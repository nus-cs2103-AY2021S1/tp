package seedu.address.model.task.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Date;

public class DateComparatorTest {

    private Date first = new Date(VALID_DATE1);
    private Date second = new Date(VALID_DATE2);
    private DateComparator dateComparator = new DateComparator();

    @Test
    public void compare() {
        assertEquals(-1, dateComparator.compare(first, second));
        assertEquals(0, dateComparator.compare(first, first));
        assertEquals(1, dateComparator.compare(second, first));
    }
}
