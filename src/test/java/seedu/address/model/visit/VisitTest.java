package seedu.address.model.visit;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.TypicalVisits;
import seedu.address.testutil.VisitBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalVisits.VISIT_1;

public class VisitTest {
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(VISIT_1.equals(VISIT_1));

        // same values -> returns true
        Visit visitCopy = new VisitBuilder(VISIT_1).build();
        assertTrue(VISIT_1.equals(visitCopy));

        // different types -> returns false
        assertFalse(VISIT_1.equals(1));

        // null -> returns false
        assertFalse(VISIT_1.equals(null));

        // different visitList -> returns false
        Visit otherVisit = TypicalVisits.VISIT_2;
        assertFalse(VISIT_1.equals(otherVisit));
    }
}
