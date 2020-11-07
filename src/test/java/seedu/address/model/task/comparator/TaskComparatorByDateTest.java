package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.todolist.TaskBuilder;

public class TaskComparatorByDateTest {

    private TaskComparatorByDate taskComparatorByDate = new TaskComparatorByDate();

    @Test
    public void compare() {
        // both have dates
        assertEquals(-1, taskComparatorByDate.compare(LAB_01, LAB_02));
        assertEquals(0, taskComparatorByDate.compare(LAB_01, LAB_01));
        assertEquals(1, taskComparatorByDate.compare(LAB_02, LAB_01));

        // only one has date
        Task editedLab01 = new TaskBuilder().withDate(null).build();
        Task editedLab02 = new TaskBuilder().withDate(null).build();

        assertEquals(1, taskComparatorByDate.compare(editedLab01, LAB_02));
        assertEquals(-1, taskComparatorByDate.compare(LAB_02, editedLab01));
        assertEquals(0, taskComparatorByDate.compare(editedLab01, editedLab02));
    }
}
