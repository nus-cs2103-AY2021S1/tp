package seedu.jarvis.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_THREE;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_FIRST;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_FIRST;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_FOURTH;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_SECOND;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_THIRD;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_THREE;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todoTest = new Todo(TEST_TASK_DESCRIPTION_FIRST);
    private final Todo todoTestTwo = new Todo(TEST_TASK_DESCRIPTION_SECOND);
    private final Todo todoTestThree = new Todo(TEST_TASK_DESCRIPTION_THIRD);
    private final Todo todoTestFour = new Todo(TEST_TASK_DESCRIPTION_FOURTH);
    private final Event eventTest = new Event(TEST_TASK_DESCRIPTION_FIRST, TEST_TASK_DATETIME_FIRST);

    //For construction of todo from CLI
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Todo(null));
    }

    //For construction of todo from .json file
    @Test
    public void constructorTest_todoEquals() {
        assertNotEquals(TEST_TODO_THREE, TEST_DEADLINE_THREE);
        assertEquals(TEST_TODO_THREE, TEST_TODO_THREE);
    }

    @Test
    void getDescription_sameSuccess() {
        assertTrue(todoTest.getDescription().equals(TEST_TASK_DESCRIPTION_FIRST));
        assertFalse(todoTestThree.getDescription().equals(TEST_TASK_DESCRIPTION_FOURTH));
    }

    @Test
    void generateTaskIdTest_uniqueTaskId() {
        String todoCurrentId = todoTest.getTaskId();
        String todoNextId = todoTest.generateTaskId();
        assertTrue(todoCurrentId != todoNextId);
    }

    @Test
    void testEquals_sameReference() {
        //same object reference -> true
        assertTrue(todoTest.equals(todoTest));

        //different object reference -> false
        assertFalse(todoTestTwo.equals(todoTestFour));

        //totally different object classes -> false
        assertFalse(todoTest.equals(eventTest));
    }

    @Test
    void testToString_todoString() {
        String taskTestToString = "[" + todoTestThree.getTaskId() + "] " + todoTestThree.getDescription();
        assertTrue(todoTestThree.toString().equals(taskTestToString));
    }
}
