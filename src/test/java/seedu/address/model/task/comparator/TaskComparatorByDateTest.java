package seedu.address.model.task.comparator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

public class TaskComparatorByDateTest {

    private TaskComparatorByDate taskComparatorByDate = new TaskComparatorByDate();

    @Test
    public void compare() {
        assertEquals(-1, taskComparatorByDate.compare(LAB_01, LAB_02));
        assertEquals(0, taskComparatorByDate.compare(LAB_01, LAB_01));
        assertEquals(1, taskComparatorByDate.compare(LAB_02, LAB_01));
    }
}
