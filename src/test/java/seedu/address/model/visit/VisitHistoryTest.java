package seedu.address.model.visit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalVisits;

public class VisitHistoryTest {
    @Test
    public void equals() {
        VisitHistory visitHistory = TypicalVisits.getTypicalVisitHistory1();

        // Null value. Returns false
        assertFalse(visitHistory.equals(null));

        // Same object. Returns true
        assertTrue(visitHistory.equals(visitHistory));

        // Same values. Returns true
        VisitHistory visitHistoryCopy = new VisitHistory(visitHistory.getVisits());
        assertTrue(visitHistory.equals(visitHistoryCopy));

        // Different types. Returns false
        assertFalse(visitHistory.equals(1));

        // Different types. Returns false
        assertFalse(visitHistory.equals("test"));

        // Different visit history. Returns false
        VisitHistory otherVisitHistory = TypicalVisits.getTypicalVisitHistory2();
        assertFalse(visitHistory.equals(otherVisitHistory));
    }
}
