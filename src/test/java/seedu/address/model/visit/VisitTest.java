package seedu.address.model.visit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMENT_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITDATE_ONE;
import static seedu.address.testutil.TypicalVisits.VISIT_1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalVisits;
import seedu.address.testutil.VisitBuilder;

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

        // different visitDate -> returns false
        Visit editedVisit = new VisitBuilder(VISIT_1).withVisitDate(VALID_VISITDATE_ONE).build();
        assertFalse(VISIT_1.equals(editedVisit));

        // different patientName -> returns false
        editedVisit = new VisitBuilder(VISIT_1).withPatientName(VALID_NAME_BOB).build();
        assertFalse(VISIT_1.equals(editedVisit));

        // different diagnosis -> returns false
        editedVisit = new VisitBuilder(VISIT_1).withDiagnosis(VALID_DIAGNOSIS_ONE).build();
        assertFalse(VISIT_1.equals(editedVisit));

        // different diagnosis -> returns false
        editedVisit = new VisitBuilder(VISIT_1).withPrescription(VALID_PRESCRIPTION_ONE).build();
        assertFalse(VISIT_1.equals(editedVisit));

        // different diagnosis -> returns false
        editedVisit = new VisitBuilder(VISIT_1).withComment(VALID_COMMENT_ONE).build();
        assertFalse(VISIT_1.equals(editedVisit));

        // different visitList -> returns false
        Visit otherVisit = TypicalVisits.VISIT_2;
        assertFalse(VISIT_1.equals(otherVisit));
    }
}
