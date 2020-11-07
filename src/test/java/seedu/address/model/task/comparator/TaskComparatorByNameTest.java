package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.todolist.TypicalTasks.ASSIGNMENT_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;

import org.junit.jupiter.api.Test;

public class TaskComparatorByNameTest {

    private TaskComparatorByName taskComparatorByName = new TaskComparatorByName();

    @Test
    public void compare() {
        assertEquals(-1, taskComparatorByName.compare(ASSIGNMENT_01, LAB_01));
        assertEquals(0, taskComparatorByName.compare(LAB_01, LAB_01));
        assertEquals(1, taskComparatorByName.compare(LAB_01, ASSIGNMENT_01));
    }
}
