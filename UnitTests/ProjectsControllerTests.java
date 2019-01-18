package UnitTests;

import ProjectSuggester.Exceptions.UserNotConnectedException;
import ProjectSuggester.Controllers.LoginController;
import ProjectSuggester.Model.Project;
import ProjectSuggester.Controllers.ProjectsController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectsControllerTests {
    private ProjectsController ProjectsController;


    @Before
    public void Init() {
        var loginController = mock(LoginController.class);
        when(loginController.isLogin(anyInt())).thenReturn(true);

        this.ProjectsController = new ProjectsController(loginController);
    }

    @Test
    public void AddProject_GoodData_GetResultID() {

        int id = AddProject();
        assertTrue("The id:" + id + " must be equal or great from 0",
                id > 0);

    }

    private int AddProject() {
        var builder = ProjectBuilder.LoadDefaults();
        return ProjectsController.Add(
                builder.getProjectName(),
                builder.getProjectDescription(),
                builder.getHours(),
                builder.getSuggesterName(),
                builder.getSuggesterMail(),
                builder.getSuggesterPhone(),
                builder.getSuggesterCompany()
        );
    }

    @Test
    public void ProjectStatus_GetStatus() {
        // Init
        var id = AddProject();

        // validate
        var status = ProjectsController.GetStatus(id);
        assertEquals("Just added project with id:" + id + " status must be " + Project.Status.InCheck + " but found " + status, Project.Status.InCheck, status);
    }

    @Test
    public void ProjectStatus_UpdateStatus_GetStatus() {
        // Init
        var id = AddProject();
        ProjectsController.UpdateStatus(id, Project.Status.Reject);

        // validate
        var status = ProjectsController.GetStatus(id);
        assertEquals(Project.Status.Reject, status);

    }

    @Test
    public void AddProject_UserNotConnected_ThrowException() {
        var loginController = mock(LoginController.class);
        when(loginController.isLogin(anyInt())).thenReturn(false);

        var projectController = new ProjectsController(loginController);

        try {
            var builder = ProjectBuilder.LoadDefaults();
            projectController.Add(
                    builder.getProjectName(),
                    builder.getProjectDescription(),
                    builder.getHours(),
                    builder.getSuggesterName(),
                    builder.getSuggesterMail(),
                    builder.getSuggesterPhone(),
                    builder.getSuggesterCompany()
            );
            fail("Should throw UserNotConnected");
        } catch (UserNotConnectedException ignored) {
        }
    }
}
