package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.Name;

public class ExerciseNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidExerciseName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidExerciseName() {
        // null exercise name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid exercise name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("Squats*")); // contains non-alphanumeric characters

        // valid exercise name
        assertTrue(Name.isValidName("squats")); // alphabets only
        assertTrue(Name.isValidName("4515")); // numbers only
        assertTrue(Name.isValidName("P90x")); // alphanumeric characters
        assertTrue(Name.isValidName("Squats")); // with capital letters
        assertTrue(Name.isValidName("F45 HIIT Session")); // long names
    }
}
