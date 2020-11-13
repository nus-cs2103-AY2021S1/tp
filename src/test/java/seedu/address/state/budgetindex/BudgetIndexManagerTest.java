package seedu.address.state.budgetindex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class BudgetIndexManagerTest {
    public static final int VALID_BUDGET_INDEX = 0;
    public static final int INVALID_BUDGET_INDEX = -1;

    @Test
    void constructor_invalidInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new BudgetIndexManager(INVALID_BUDGET_INDEX));
    }

    @Test
    void setIndex_validInput() {
        BudgetIndex actual = new BudgetIndexManager(1);
        actual.setIndex(VALID_BUDGET_INDEX);
        BudgetIndex expected = new BudgetIndexStub().getTypicalBudgetIndex();
        assertEquals(expected, actual);
    }

    @Test
    void setIndex_invalidInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new BudgetIndexManager(VALID_BUDGET_INDEX)
                .setIndex(INVALID_BUDGET_INDEX));
    }

    @Test
    void getIndex() {
        BudgetIndex expected = new BudgetIndexStub().getTypicalBudgetIndex();
        assertEquals(expected.getBudgetIndex(), Optional.of(0));
    }

    @Test
    void equals_sameObject_true() {
        BudgetIndex budgetIndex = new BudgetIndexStub().getTypicalBudgetIndex();
        assertEquals(true, budgetIndex.equals(budgetIndex));
    }

    @Test
    void equals_null_false() {
        BudgetIndex budgetIndex = new BudgetIndexStub().getTypicalBudgetIndex();
        assertEquals(false, budgetIndex.equals(null));
    }

    class BudgetIndexStub {
        public final BudgetIndex getTypicalBudgetIndex() {
            return new BudgetIndexManager(VALID_BUDGET_INDEX);
        }
    }
}
