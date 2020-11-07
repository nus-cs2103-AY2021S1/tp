package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import org.junit.jupiter.api.Test;

public class TaskComparatorByPriorityTest {

    private TaskComparatorByPriority taskComparatorByPriority = new TaskComparatorByPriority();

    @Test
    public void compare() {
        assertEquals(-1, taskComparatorByPriority.compare(LAB_01, LAB_02));
        assertEquals(0, taskComparatorByPriority.compare(LAB_01, LAB_01));
        assertEquals(1, taskComparatorByPriority.compare(LAB_02, LAB_01));
    }
}
