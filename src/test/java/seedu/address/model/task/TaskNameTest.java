package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB07;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only
        assertFalse(TaskName.isValidTaskName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // boundary value (length = 31)

        // valid name
        assertTrue(TaskName.isValidTaskName("Laundry")); // single word
        assertTrue(TaskName.isValidTaskName("Finish Lab01")); // with whitespace
        assertTrue(TaskName.isValidTaskName("ASSIGNMENT01")); // all uppercase
        assertTrue(TaskName.isValidTaskName("Prepare team meeting")); // 3 words
        assertTrue(TaskName.isValidTaskName("Review Chapter8 for final exam")); // boundary value (length = 30)
    }

    @Test
    public void equals() {
        TaskName first = new TaskName(VALID_NAME_LAB05);
        TaskName second = new TaskName(VALID_NAME_LAB07);

        // null value -> false
        assertFalse(first.equals(null));

        // other type -> false
        assertFalse(first.equals(10));

        // same task name -> true
        assertTrue(first.equals(first));

        // different task name -> false
        assertFalse(first.equals(second));
    }

    @Test
    public void test_toString() {
        TaskName name = new TaskName(VALID_NAME_LAB05);
        String expectedString = VALID_NAME_LAB05;

        assertEquals(expectedString, name.toString());
    }
}
