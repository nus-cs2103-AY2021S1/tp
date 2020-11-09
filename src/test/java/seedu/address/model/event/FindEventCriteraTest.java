package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FindEventCriteraTest {
    @Test
    public void addPredicate_nullPredicate_throwsNullPointerException() {
        FindEventCriteria findEventCriteria = new FindEventCriteria();
        assertThrows(NullPointerException.class, () -> findEventCriteria.addPredicate(null));
    }

    @Test
    public void equals() {
        FindEventCriteria firstFindEventCriteria = new FindEventCriteria();
        FindEventCriteria secondFindEventCriteria = new FindEventCriteria();

        // same object -> returns true
        assertTrue(firstFindEventCriteria.equals(firstFindEventCriteria));

        // null -> returns false
        assertFalse(firstFindEventCriteria.equals(null));

        // different type -> returns false
        assertFalse(firstFindEventCriteria.equals(10));

        // same value -> returns true
        assertTrue(firstFindEventCriteria.equals(secondFindEventCriteria));
    }
}
