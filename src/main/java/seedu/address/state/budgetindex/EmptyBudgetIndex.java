package seedu.address.state.budgetindex;

import java.util.Optional;

public class EmptyBudgetIndex implements BudgetIndex {
    @Override
    public void setIndex(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Integer> getBudgetIndex() {
        return Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmptyBudgetIndex;
    }
}
