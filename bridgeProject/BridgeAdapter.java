package bridgeProject;

import ProjectSuggester.Controllers.LoginController;
import ProjectSuggester.Controllers.ProjectsController;
import ProjectSuggester.Controllers.UsersController;
import acptTests.auxiliary.DBRegisteredProjectInfo;
import acptTests.auxiliary.DBSuggestedProjectInfo;

import java.time.Duration;

public class BridgeAdapter implements BridgeProject {
    private UsersController usersController = new UsersController();
    private ProjectsController projectsController = new ProjectsController();
    private LoginController loginController = new LoginController();

    @Override
    public void registerNewTechnicalAdviser(String user, String password) {
        usersController.AddMentor(user, password);
    }

    @Override
    public void addNewStudent(String user, String password) {
        usersController.AddStudent(user, password);
    }

    public int addNewProject(String user, String pass, DBSuggestedProjectInfo suggestedProject) {
        try {
            loginController.login(user, pass);

            return projectsController.Add(
                    user,
                    suggestedProject.projectName,
                    suggestedProject.description,
                    Duration.ofHours(suggestedProject.numberOfHours),
                    suggestedProject.firstName + " " + suggestedProject.lastName,
                    suggestedProject.email,
                    suggestedProject.phone,
                    suggestedProject.organization);
        } catch (Exception e) {
            System.out.print(e.getClass().getName() + ":");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int registerToProject(String user, String pass, DBRegisteredProjectInfo registeredProject) {
        try {

            loginController.login(user, pass);

            return Math.abs(projectsController.Register(user,
                    registeredProject.projectId,
                    registeredProject.academicAdviser,
                    registeredProject.studentList.toArray(String[]::new)
            ).hashCode());
        } catch (Exception e) {
            System.out.print(e.getClass().getName() + ":");
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
