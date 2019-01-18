package UnitTests;

import ProjectSuggester.Project;
import ProjectSuggester.ProjectsController;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectsControllerTests {
    private ProjectsController ProjectsController;

    @Before
    public void Init() {
        this.ProjectsController = new ProjectsController();
    }

    @Test
    public void AddProject_GoodData_GetResultID() throws InvalidKeyException {

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
        assertEquals("Just added project status must be " + Project.Status.InCheck + " but found " + status, Project.Status.InCheck, status);
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
}
