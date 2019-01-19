package UnitTests.Controller;

import ProjectSuggester.Controllers.LoginController;
import ProjectSuggester.Controllers.ProjectsController;
import ProjectSuggester.Exceptions.UserNotConnectedException;
import ProjectSuggester.Model.Project;
import UnitTests.ProjectBuilder;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectsControllerTests {
    private ProjectsController ProjectsController;


    @Before
    public void Init() {
        var loginController = mock(LoginController.class);
        when(loginController.isLogin(anyString())).thenReturn(true);

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
                builder.getSuggesterMail(),
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
    public void AddProject_WithoutCompany_ShouldPass() {
        var builder = ProjectBuilder.LoadDefaults();
        var id = ProjectsController.Add(
                builder.getSuggesterMail(),
                builder.getProjectName(),
                builder.getProjectDescription(),
                builder.getHours(),
                builder.getSuggesterName(),
                builder.getSuggesterMail(),
                builder.getSuggesterPhone(),
                null
        );
        assertTrue(id > 0);
    }

    @Test
    public void AddProject_UserNotConnected_ThrowException() {
        var loginController = mock(LoginController.class);
        when(loginController.isLogin(anyString())).thenReturn(false);

        var projectController = new ProjectsController(loginController);

        try {
            var builder = ProjectBuilder.LoadDefaults();
            projectController.Add(
                    builder.getSuggesterMail(),
                    builder.getProjectName(),
                    builder.getProjectDescription(),
                    builder.getHours(),
                    builder.getSuggesterName(),
                    builder.getSuggesterMail(),
                    builder.getSuggesterPhone(),
                    builder.getSuggesterCompany()
            );
            fail("Should throw UserNotConnected");
        } catch (UserNotConnectedException | NoSuchElementException ignored) {
        }
    }

    @Test
    public void AddProject_CreationTime_ShouldAdded() {
        // init
        var id = AddProject();

        // Validate
        var date = ProjectsController.GetCreationDate(id);
        assertNotNull(date);
    }

    @Test
    public void AddProject_SameNameYearSuggester_ShouldFail() {
        // Init
        var id = AddProject();
        var year = ProjectsController.GetCreationDate(id);
        var projectName = ProjectsController.GetProjectName(id);
        var suggester = ProjectsController.GetSuggesterForProject(id);

        // Validate
        var builder = ProjectBuilder.LoadDefaults();

        try {
            ProjectsController.Add(
                    builder.getSuggesterMail(),
                    projectName,
                    builder.getProjectDescription(),
                    builder.getHours(),
                    builder.getSuggesterName(),
                    suggester.getUsername(),
                    builder.getSuggesterPhone(),
                    builder.getSuggesterCompany()
            );
            fail("This should fail");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            ProjectsController.Add(
                    builder.getSuggesterMail(),
                    projectName,
                    builder.getProjectDescription(),
                    builder.getHours(),
                    builder.getSuggesterName(),
                    builder.getSuggesterMail(),
                    builder.getSuggesterPhone(),
                    suggester.getCompany().getName()
            );
            fail("This should fail");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void RegisterToProject_StudentsAndMentor() {
        // Init
        var projectId = AddProject();


        // Validate
        Faker faker = new Faker(Locale.forLanguageTag("he"));

        var stuentt1 = faker.idNumber().valid();
        var registrationID = ProjectsController.Register(stuentt1,
                projectId,
                faker.name().fullName(),
                stuentt1,
                faker.idNumber().valid()
        );
        assertNotNull(registrationID);
    }

    @Test
    public void Register_UserNotConnected_ThrowException() {
        // Init
        var projectId = AddProject();

        var loginController = mock(LoginController.class);
        when(loginController.isLogin(anyString())).thenReturn(false);

        var projectController = new ProjectsController(loginController);

        // Validate
        try {
            Faker faker = new Faker(Locale.forLanguageTag("he"));
            var student1 = faker.idNumber().valid();
            projectController.Register(student1,
                    projectId,
                    faker.name().fullName(),
                    student1,
                    faker.idNumber().valid()
            );
            fail("Should throw UserNotConnected");
        } catch (UserNotConnectedException ignored) {
        }
    }

    @Test
    public void Register_OneStudent_ShouldFail() {
        var projectId = AddProject();

        try {
            Faker faker = new Faker(Locale.forLanguageTag("he"));
            var student1 = faker.idNumber().valid();
            ProjectsController.Register(student1,
                    projectId,
                    faker.name().fullName(),
                    student1
            );
            fail("Should throw UserNotConnected");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void Register_MentorAlreadyRegisterToProject_ShouldFail() {
        // init
        Faker faker = new Faker(Locale.forLanguageTag("he"));

        var mentorName = faker.name().fullName();
        var projectId = AddProject();
        ProjectsController.RegisterMentor(projectId, mentorName);

        var student1 = faker.idNumber().valid();
        ProjectsController.Register(student1,
                projectId,
                mentorName,
                student1,
                faker.idNumber().valid()
        );

        try {
            var student2 = faker.idNumber().valid();

            ProjectsController.Register(student2,
                    projectId,
                    mentorName + faker.name().fullName(),
                    student2,
                    faker.idNumber().valid()
            );
            fail("Should throw UserNotConnected");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void Register_StudentsAlreadyRegistered_ShouldFail() {
        Faker faker = new Faker(Locale.forLanguageTag("he"));

        var projectId = AddProject();
        var mentorName = faker.name().fullName();

        var student1 = faker.idNumber().valid();

        ProjectsController.Register(student1,
                projectId,
                mentorName,
                student1,
                faker.idNumber().valid()
        );

        try {
            var student2 = faker.idNumber().valid();

            ProjectsController.Register(student2,
                    projectId,
                    mentorName,
                    student2,
                    faker.idNumber().valid()
            );
            fail("Should fail");
        } catch (IllegalArgumentException ignored) {
        }
    }
}
