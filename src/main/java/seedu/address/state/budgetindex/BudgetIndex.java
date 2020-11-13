package seedu.address.state.budgetindex;

import java.util.Optional;

public interface BudgetIndex {
    Optional<Integer> getBudgetIndex();
    void setIndex(int index);
}
