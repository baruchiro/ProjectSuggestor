package UnitTests;

import static org.junit.Assert.fail;

public interface IDtoTest {

    default void ShouldThrowException(ProjectBuilder builder) {
        try {
            builder.Build();
            fail("This should fail");
        } catch (IllegalArgumentException ignored) {
        }
    }
}
