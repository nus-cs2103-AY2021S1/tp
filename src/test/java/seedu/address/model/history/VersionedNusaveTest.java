package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Nusave;
import seedu.address.state.State;
import seedu.address.testutil.TypicalBudgets;
import seedu.address.testutil.TypicalState;

class VersionedNusaveTest {
    public static final Nusave VALID_NUSAVE = TypicalBudgets.getTypicalNusave();
    public static final State VALID_MAIN_PAGE_STATE = TypicalState.getTypicalMainPageState();
    public static final State VALID_BUDGET_PAGE_STATE_AT_INDEX_ONE = TypicalState.getKfcBudgetPageState();

    @Test
    void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VersionedNusave(null, VALID_NUSAVE));
        assertThrows(NullPointerException.class, () -> new VersionedNusave(VALID_MAIN_PAGE_STATE, null));
    }

    @Test
    void getBudgetIndex_withIndexOneState_returnsCorrectly() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_BUDGET_PAGE_STATE_AT_INDEX_ONE, VALID_NUSAVE);
        assertEquals(versionedNusave.getBudgetIndex(), VALID_BUDGET_PAGE_STATE_AT_INDEX_ONE.getBudgetIndex());
    }

    @Test
    void getBudgetIndex_withEmptyIndexState_returnsCorrectly() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_MAIN_PAGE_STATE, VALID_NUSAVE);
        assertEquals(versionedNusave.getBudgetIndex(), VALID_MAIN_PAGE_STATE.getBudgetIndex());
    }

    @Test
    void getBudgetList_withValidNusave_returnsCorrectly() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_MAIN_PAGE_STATE, VALID_NUSAVE);
        assertEquals(versionedNusave.getBudgetList(), VALID_NUSAVE.getBudgetList());
    }

    @Test
    void equals_withSameNusave_returnsTrue() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_MAIN_PAGE_STATE, VALID_NUSAVE);
        assertTrue(versionedNusave.equals(versionedNusave));
    }

    @Test
    void equals_withValidNusave_returnsTrue() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_MAIN_PAGE_STATE, VALID_NUSAVE);
        VersionedNusave toCompare = new VersionedNusave(TypicalState.getTypicalMainPageState(),
                TypicalBudgets.getTypicalNusave());
        assertTrue(versionedNusave.equals(toCompare));
    }

    @Test
    void equals_withNull_returnsFalse() {
        VersionedNusave versionedNusave = new VersionedNusave(VALID_MAIN_PAGE_STATE, VALID_NUSAVE);
        assertFalse(versionedNusave.equals(null));
    }
}
