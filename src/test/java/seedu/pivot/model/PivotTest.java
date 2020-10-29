package seedu.pivot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE_PAULINE_ASSAULT;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.exceptions.DuplicateCaseException;
import seedu.pivot.testutil.CaseBuilder;

public class PivotTest {

    private final Pivot pivot = new Pivot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), pivot.getCaseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pivot.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPivot_replacesData() {
        Pivot newData = getTypicalPivot();
        pivot.resetData(newData);
        assertEquals(newData, pivot);
    }

    @Test
    public void resetData_withDuplicateCases_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND).build();
        List<Case> newCases = Arrays.asList(ALICE_PAULINE_ASSAULT, editedAlice);
        PivotStub newData = new PivotStub(newCases);

        assertThrows(DuplicateCaseException.class, () -> pivot.resetData(newData));
    }

    @Test
    public void hasCase_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pivot.hasCase(null));
    }

    @Test
    public void hasCase_caseNotInPivot_returnsFalse() {
        assertFalse(pivot.hasCase(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void hasCase_caseInPivot_returnsTrue() {
        pivot.addCase(ALICE_PAULINE_ASSAULT);
        assertTrue(pivot.hasCase(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void hasCase_caseWithSameIdentityFieldsInPivot_returnsTrue() {
        pivot.addCase(ALICE_PAULINE_ASSAULT);
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(pivot.hasCase(editedAlice));
    }

    @Test
    public void getCaseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> pivot.getCaseList().remove(0));
    }

    /**
     * A stub ReadOnlyPivot whose persons list can violate interface constraints.
     */
    static class PivotStub implements ReadOnlyPivot {
        private final ObservableList<Case> cases = FXCollections.observableArrayList();

        PivotStub(Collection<Case> cases) {
            this.cases.setAll(cases);
        }

        @Override
        public ObservableList<Case> getCaseList() {
            return cases;
        }
    }

}
