package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Since Exercise, Lesson and Routine is built around this Name class.
 * The tests for Name is done centrally in this class, and possible
 * inputs of names for the 3 classes will be tested here.
 */
public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("cs1234*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("Bench Press @ Record: 45KG")); //Possible Exercise names
        assertFalse(Name.isValidName("Push Routine @ The Beach / Park")); //Possible Routine names


        // valid name
        assertTrue(Name.isValidName("cs")); // alphabets only
        assertTrue(Name.isValidName("1234")); // numbers only
        assertTrue(Name.isValidName("cs1234")); // alphanumeric characters
        assertTrue(Name.isValidName("CS1234")); // with capital letters
        assertTrue(Name.isValidName("CS1234 at Auditorium in the morning")); // long names
        assertTrue(Name.isValidName("Pull Ups at the fitness corner at East Coast Park")); // long names
        assertTrue(Name.isValidName("Chest routine that follows Mike Chang workout on YouTube")); // long names
        assertTrue(Name.isValidName("Bench Press 45KG PER SIDE BEST ATTEMPT")); // long names
        assertTrue(Name.isValidName("Abs Workout at fitness corner")); // long names

    }

    @Test
    public void constraintsTesting() {

        //Name with less than 50 characters acceptable
        assertDoesNotThrow(() -> new Name("A very good name"));

        //Names with more than 50 characters not acceptable
        assertThrows(IllegalArgumentException.class, () ->
                new Name("A not so good name that is too long to be accepted by fitNUS definitely"));

        //Names with special characters are not acceptable
        assertThrows(IllegalArgumentException.class, () ->
                new Name("N@T SUCH A GOOD N@ME"));
    }

    @Test
    public void equalityChecks() {
        Name modelName = new Name("Model Name");
        Name modelNameCopy = new Name("Model Name");
        Name differentName = new Name("Different Name");

        //Same object
        assertEquals(modelName, modelName);
        assertEquals(modelName.hashCode(), modelName.hashCode());
        assertEquals(modelName.toString(), modelNameCopy.toString());

        //Same String, different object
        assertEquals(modelName, modelNameCopy);
        assertEquals(modelName.hashCode(), modelNameCopy.hashCode());
        assertEquals(modelName.toString(), modelNameCopy.toString());

        //Different String, different object
        assertNotEquals(modelName, differentName);
        assertNotEquals(modelName.hashCode(), differentName.hashCode());
        assertNotEquals(modelName.toString(), differentName.toString());

        //Null comparisons
        assertNotEquals(modelName, null);
        assertNotEquals(modelName.toString(), null);
    }
}
