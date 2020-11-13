package seedu.address.state.budgetindex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class EmptyBudgetIndexTest {

    @Test
    void setIndex_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> new EmptyBudgetIndex().setIndex(1));
    }

    @Test
    void getBudgetIndex() {
        assertEquals(Optional.empty(),
                new EmptyBudgetIndex().getBudgetIndex());
    }
}
