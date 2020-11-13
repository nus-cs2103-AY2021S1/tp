package seedu.jarvis.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.model.task.Task.NO_DATE_TIME_DESCRIPTION;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DATETIME_FIRST;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TASK_DESCRIPTION_FIRST;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private final String typeTodo = "T";
    private final String typeEvent = "E";
    private final String typeDeadline = "D";
    private final Todo todoTest = new Todo(TEST_TASK_DESCRIPTION_FIRST);
    private final Event eventTest = new Event(TEST_TASK_DESCRIPTION_FIRST, TEST_TASK_DATETIME_FIRST);
    private final Deadline deadlineTest = new Deadline(TEST_TASK_DESCRIPTION_FIRST, TEST_TASK_DATETIME_FIRST);

    @Test
    public void taskNumIncTest_incByOne() {
        int prev = Task.getTaskNum();
        Task.taskNumInc();
        assertEquals(Task.getTaskNum(), prev + 1);
    }

    // Compare task numbers read from .json data file and the current task number stored.
    // And then sets the stored task number based on the larger task number read +1.
    @Test
    // Simulate Todo read from .json data file, new task num == stored task num
    public void compareAndSet_taskNum_todoEqualStored() {
        int storedTaskNum = Task.getTaskNum();
        String simulatedNewTaskId = typeTodo + storedTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        // Ensures that storedTaskNum has an +1 increment
        assertFalse(afterComparisonTaskNum < storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum + 1);
    }

    @Test
    // Simulate Todo read from .json data file, new task num > stored task num
    public void compareAndSet_taskNum_todoGreaterStored() {
        int storedTaskNum = Task.getTaskNum();
        int taskNumInc = 100;
        int newTaskNum = storedTaskNum + taskNumInc;
        String simulatedNewTaskId = typeTodo + newTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        assertTrue(afterComparisonTaskNum > storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum + taskNumInc + 1);
    }

    @Test
    // Simulate Event read from .json data file, new task num > stored task num
    public void compareAndSet_taskNum_eventGreaterStored() {
        int storedTaskNum = Task.getTaskNum();
        int taskNumInc = 1;
        int newTaskNum = storedTaskNum + taskNumInc;
        String simulatedNewTaskId = typeEvent + newTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        assertTrue(afterComparisonTaskNum > storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum + taskNumInc + 1);
    }

    @Test
    // Simulate Deadline read from .json data file, new task num > stored task num
    public void compareAndSet_taskNum_deadlineGreaterStored() {
        int storedTaskNum = Task.getTaskNum();
        int taskNumInc = 5;
        int newTaskNum = storedTaskNum + taskNumInc;
        String simulatedNewTaskId = typeDeadline + newTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        assertTrue(afterComparisonTaskNum > storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum + taskNumInc + 1);
    }

    @Test
    // Simulate Event read from .json data file, new task num < stored task num
    public void compareAndSet_taskNum_eventSmallerStored() {
        int storedTaskNum = Task.getTaskNum();
        int taskNumInc = -1;
        int newTaskNum = storedTaskNum + taskNumInc;
        String simulatedNewTaskId = typeEvent + newTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        assertFalse(afterComparisonTaskNum > storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum);
    }

    @Test
    // Simulate Deadline read from .json data file, new task num < stored task num
    public void compareAndSet_taskNum_deadlineSmallerStored() {
        int storedTaskNum = Task.getTaskNum();
        int taskNumInc = -100;
        int newTaskNum = storedTaskNum + taskNumInc;
        String simulatedNewTaskId = typeDeadline + newTaskNum;

        Task.compareTaskNum(simulatedNewTaskId);
        int afterComparisonTaskNum = Task.getTaskNum();

        assertFalse(afterComparisonTaskNum > storedTaskNum);
        assertEquals(afterComparisonTaskNum, storedTaskNum);
    }

    @Test
    public void getTypeTest_todoEventDeadline() {
        assertEquals(Task.getType(todoTest), typeTodo);
        assertEquals(Task.getType(eventTest), typeEvent);
        assertEquals(Task.getType(deadlineTest), typeDeadline);
        Task taskTest = todoTest;
        assertEquals(Task.getType(taskTest), typeTodo);
    }

    @Test
    public void getUnformattedPossibleDateTimeTest_eventDeadlineHasDateTime() {
        // Event and Deadline have unformatted date time
        assertEquals(eventTest.getUnformattedPossibleDateTime(eventTest), TEST_TASK_DATETIME_FIRST.toString());
        assertEquals(eventTest.getUnformattedPossibleDateTime(eventTest), eventTest.getUnformattedDateTime());
        assertEquals(deadlineTest.getUnformattedPossibleDateTime(deadlineTest), TEST_TASK_DATETIME_FIRST.toString());
        assertEquals(deadlineTest.getUnformattedPossibleDateTime(deadlineTest), deadlineTest.getUnformattedDateTime());
    }

    @Test
    public void getUnformattedPossibleDateTimeTest_todoHasNoDateTime() {
        // Todo has no unformatted date time
        assertEquals(todoTest.getUnformattedPossibleDateTime(todoTest), NO_DATE_TIME_DESCRIPTION);
    }

    @Test
    public void getFormattedPossibleDateTimeTest_eventDeadlineHasDateTime() {
        // Event and Deadline have unformatted date time
        assertEquals(eventTest.getFormattedPossibleDateTime(eventTest), eventTest.getDateTime());
        assertEquals(deadlineTest.getFormattedPossibleDateTime(deadlineTest), deadlineTest.getDateTime());
    }

    @Test
    public void getFormattedPossibleDateTimeTest_todoHasNoDateTime() {
        // Todo has no unformatted date time
        assertEquals(todoTest.getFormattedPossibleDateTime(todoTest), NO_DATE_TIME_DESCRIPTION);
    }

}
