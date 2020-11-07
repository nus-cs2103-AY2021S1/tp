package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ASSIGNMENT01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.TaskName;

public class TaskNameComparatorTest {

    private TaskName first = new TaskName(VALID_NAME_LAB05);
    private TaskName second = new TaskName(VALID_NAME_ASSIGNMENT01);
    private TaskNameComparator taskNameComparator = new TaskNameComparator();

    @Test
    public void compare() {
        assertEquals(-1, taskNameComparator.compare(first, second));
        assertEquals(0, taskNameComparator.compare(first, first));
        assertEquals(1, taskNameComparator.compare(second, first));
    }
}
