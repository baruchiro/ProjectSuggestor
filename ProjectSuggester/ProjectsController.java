package ProjectSuggester;

import java.time.Duration;
import java.util.NoSuchElementException;

public class ProjectsController {


    public int Add(String projectName, String projectDescription, Duration hours, String suggesterName, String suggesterMail, String suggesterPhone, String suggesterCompany) {
        var t = new LoginController().isLogin(1);
        var id = -1;
        if (DB.getInstance().projects.size() == 0) {
            id = 1;
        } else {
            var max = DB.getInstance().projects.stream().map(Project::getId).max(Integer::compareTo);
            id = max.get() + 1;
        }

        var company = new Company(suggesterCompany);
        var suggester = new Suggester(suggesterName, suggesterMail, suggesterPhone, company);
        var project = new Project(id, projectName, projectDescription, hours, suggester);
        DB.getInstance().projects.add(project);
        return id;
    }

    public Project.Status GetStatus(int id) {
        var first = DB.getInstance().projects.stream().filter(project -> project.getId() == id).findFirst();
        if (first.isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.get().getStatus();
    }

    public void UpdateStatus(int id, Project.Status status) {
        var first = DB.getInstance().projects.stream().filter(project -> project.getId() == id).findFirst();
        if (first.isEmpty()) {
            throw new NoSuchElementException();
        }
        first.get().setStatus(status);
    }
}
