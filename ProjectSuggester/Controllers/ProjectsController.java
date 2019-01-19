package ProjectSuggester.Controllers;

import ProjectSuggester.DB;
import ProjectSuggester.Exceptions.UserNotConnectedException;
import ProjectSuggester.Model.Company;
import ProjectSuggester.Model.Project;
import ProjectSuggester.Model.Suggester;

import java.time.Duration;
import java.util.Calendar;
import java.util.UUID;

public class ProjectsController {


    private final LoginController loginController;
    private final UsersController usersController;

    public ProjectsController() {
        this(new LoginController(), new UsersController());
    }

    public ProjectsController(LoginController loginController) {
        this(loginController, new UsersController());
    }

    public ProjectsController(LoginController loginController, UsersController usersController) {
        this.loginController = loginController;
        this.usersController = usersController;
    }

    public int Add(String projectName, String projectDescription, Duration hours, String suggesterName, String suggesterMail, String suggesterPhone, String suggesterCompany) {
        if (!loginController.isLogin(suggesterMail)) throw new UserNotConnectedException();

        var sameNameSuggesterYear = DB.getInstance().getProjectsByName(projectName)
                .filter(project ->
                        project.getSuggester().getMail().equals(suggesterMail) || project.getSuggester().getCompany().getName().equals(suggesterCompany))
                .anyMatch(project -> project.getCreationDate().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
        if (sameNameSuggesterYear) throw new IllegalArgumentException("Same name suggester year");

        var company = suggesterCompany == null ? null : new Company(suggesterCompany);
        var suggester = new Suggester(suggesterName, suggesterMail, suggesterPhone, company);
        var project = new Project(projectName, projectDescription, hours, suggester);
        return DB.getInstance().addProject(project);
    }

    public Project.Status GetStatus(int id) {
        return DB.getInstance().getProjectById(id).getStatus();
    }

    public void UpdateStatus(int id, Project.Status status) {
        DB.getInstance().getProjectById(id).setStatus(status);
    }

    public Calendar GetCreationDate(int id) {
        return DB.getInstance().getProjectById(id).getCreationDate();
    }

    public Suggester GetSuggesterForProject(int projectId) {
        return DB.getInstance().getProjectById(projectId).getSuggester();
    }

    public String GetProjectName(int id) {
        return DB.getInstance().getProjectById(id).getProjectName();
    }

    public UUID Register(int projectId, String mentorName, String... students) {
        if (students.length < 2) throw new IllegalArgumentException("Must 2 students or more");
        if (!loginController.isLogin(students[0])) throw new UserNotConnectedException();


        var project = DB.getInstance().getProjectById(projectId);
        project.setStudents(students);
        project.setMentor(mentorName);
        var uuid = UUID.randomUUID();
        project.setUUID(uuid);
        return uuid;
    }

    public void RegisterMentor(int projectId, String mentorName) {
        DB.getInstance().getProjectById(projectId).setMentor(mentorName);
    }
}
