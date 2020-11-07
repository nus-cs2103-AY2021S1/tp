package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.todolist.TaskBuilder;

public class TaskComparatorByPriorityTest {

    private TaskComparatorByPriority taskComparatorByPriority = new TaskComparatorByPriority();

    @Test
    public void compare() {
        assertEquals(-1, taskComparatorByPriority.compare(LAB_01, LAB_02));
        assertEquals(0, taskComparatorByPriority.compare(LAB_01, LAB_01));
        assertEquals(1, taskComparatorByPriority.compare(LAB_02, LAB_01));

        Task editedLab01 = new TaskBuilder().withPriority(null).build();
        Task editedLab02 = new TaskBuilder().withPriority(null).build();

        // only one has priority
        assertEquals(1, taskComparatorByPriority.compare(editedLab01, LAB_02));
        assertEquals(-1, taskComparatorByPriority.compare(LAB_02, editedLab01));

        // both don't have priority
        assertEquals(0, taskComparatorByPriority.compare(editedLab01, editedLab02));
    }
}
