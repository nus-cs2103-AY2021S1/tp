package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectDescription(null));
    }

    @Test
    public void constructor_invalidProjectDescription_throwsIllegalArgumentException() {
        String projectDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectDescription(projectDescription));
    }

    @Test
    public void isValidProjectDescription() {
        // null projectDescription
        assertThrows(NullPointerException.class, () -> ProjectDescription.isValidProjectDescription(null));

        // invalid projectDescription
        assertFalse(ProjectDescription.isValidProjectDescription("")); // empty string
        assertFalse(ProjectDescription.isValidProjectDescription(" ")); // spaces only
        assertFalse(ProjectDescription.isValidProjectDescription(" this")); // space before project description

        // valid projectDescription
        assertTrue(ProjectDescription.isValidProjectDescription("this project is amazing x10"));
        assertTrue(ProjectDescription.isValidProjectDescription("-")); // one character
        assertTrue(ProjectDescription.isValidProjectDescription("Tesla is probably the most overvalued company on "
            + "the planet, but the sentiment among many investors is the contrary")); // long project description
    }
}
