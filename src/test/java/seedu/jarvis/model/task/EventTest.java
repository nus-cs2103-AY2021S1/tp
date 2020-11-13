package seedu.jarvis.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalTasks.FORMATTED_DATETIME_ONE;
import static seedu.jarvis.testutil.TypicalTasks.FORMATTED_DATETIME_TWO;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_THREE;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_THREE;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_FIRST;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_FOURTH;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_SECOND;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_THIRD;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_FIRST;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_FOURTH;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_SECOND;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_THIRD;

import org.junit.jupiter.api.Test;

public class EventTest {
    private final Event eventTest = new Event(TEST_TASK_DESCRIPTION_FIRST, TEST_TASK_DATETIME_FIRST);
    private final Event eventTestTwo = new Event(TEST_TASK_DESCRIPTION_SECOND, TEST_TASK_DATETIME_SECOND);
    private final Event eventTestThree = new Event(TEST_TASK_DESCRIPTION_THIRD, TEST_TASK_DATETIME_THIRD);
    private final Event eventTestFour = new Event(TEST_TASK_DESCRIPTION_FOURTH, TEST_TASK_DATETIME_FOURTH);

    //For constructing Event from CLI
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, null));
        assertThrows(NullPointerException.class, () -> new Event(null, TEST_TASK_DATETIME_FIRST));
        assertThrows(NullPointerException.class, () -> new Event(TEST_TASK_DESCRIPTION_FIRST, null));
    }

    //For constructing Event from .json file
    @Test
    public void constructorTest_eventEquals() {
        assertNotEquals(TEST_EVENT_THREE, TEST_DEADLINE_THREE);
        assertEquals(TEST_EVENT_THREE, TEST_EVENT_THREE);
    }

    @Test
    void getDescriptionTest_sameSuccess() {
        assertTrue(eventTest.getDescription().equals(TEST_TASK_DESCRIPTION_FIRST));
        assertFalse(eventTestThree.getDescription().equals(TEST_TASK_DESCRIPTION_FOURTH));
    }

    @Test
    void getDateTime_sameSuccess() {
        assertTrue(eventTestTwo.getDateTime().equals(FORMATTED_DATETIME_TWO));
        assertFalse(eventTestFour.getDateTime().equals(FORMATTED_DATETIME_ONE));
    }

    @Test
    void generateTaskIdTest_uniqueTaskId() {
        String eventCurrentId = eventTest.getTaskId();
        String eventNextId = eventTest.generateTaskId();
        assertTrue(eventCurrentId != eventNextId);
    }

    @Test
    void testEquals_sameReference() {
        //same object reference -> true
        assertTrue(eventTest.equals(eventTest));

        //different object refernce -> false
        assertFalse(eventTestTwo.equals(eventTestFour));
    }

    @Test
    void testToString_eventString() {
        String taskTestToString = "[" + eventTestThree.getTaskId() + "] " + eventTestThree.getDescription()
                + " at " + eventTestThree.getDateTime();
        assertTrue(eventTestThree.toString().equals(taskTestToString));
    }
}
