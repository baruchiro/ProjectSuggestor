package UnitTests;

import org.junit.Test;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.fail;

public class DTOTests {
    @Test
    public void Project_id_GrateThenZero() {
        ShouldThrowException(ProjectBuilder.LoadDefaults().setId(0));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setId(0 - new Random().nextInt()));
    }

    @Test
    public void Project_ProjectName_NotNullOrEmpty() {
        ShouldThrowException(ProjectBuilder.LoadDefaults().setProjectName(null));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setProjectName(""));
    }

    @Test
    public void Project_ProjectDescription_NotNullOrEmpty() {
        ShouldThrowException(ProjectBuilder.LoadDefaults().setProjectDescription(null));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setProjectDescription(""));
    }


    @Test
    public void Project_Hours_Between200To300() {
        ShouldThrowException(ProjectBuilder.LoadDefaults().setHours(null));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setHours(Duration.ZERO));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setHours(Duration.ofHours(199)));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setHours(Duration.ofHours(301)));
        ShouldThrowException(ProjectBuilder.LoadDefaults().setHours(Duration.ofHours(-150)));
        ProjectBuilder.LoadDefaults().setHours(Duration.ofMinutes(15000)).Build(); //Should pass- 250 hours
    }

    private void ShouldThrowException(ProjectBuilder builder) {
        try {
            builder.Build();
            fail("This should fail");
        } catch (IllegalArgumentException ignored) {
        }
    }
}
