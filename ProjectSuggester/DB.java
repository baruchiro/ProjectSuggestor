package ProjectSuggester;

import ProjectSuggester.Model.Model;
import ProjectSuggester.Model.Project;
import ProjectSuggester.Model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class DB {
    private static DB ourInstance = new DB();
    private List<Project> projects;
    private List<User> users;

    private DB() {
        this.projects = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static DB getInstance() {
        return ourInstance;
    }

    private static <T extends Model> int AddToList(List<T> list, T element) {
        var id = list.size() == 0 ?
                1 :
                list.stream().map(T::getId).max(Integer::compareTo).get() + 1;
        element.setId(id);
        list.add(element);
        return id;
    }

    public int addProject(Project project) {
        project.setCreationDate(Calendar.getInstance());
        return AddToList(projects, project);
    }

    public Project getProjectById(int id) {
        var first = projects.stream().filter(project -> project.getId() == id).findFirst();
        if (first.isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.get();
    }

    public User getUserByMail(String mail) {
        var first = users.stream().filter(user -> user.getMail().equals(mail)).findFirst();
        if (first.isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.get();
    }

    public int addUser(User user) {
        return AddToList(users, user);
    }

    public Stream<Project> getProjectsByName(String projectName) {
        return projects.stream().filter(project -> project.getProjectName().equals(projectName));
    }
}
