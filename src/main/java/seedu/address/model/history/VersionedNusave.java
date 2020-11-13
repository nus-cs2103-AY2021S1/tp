package seedu.address.model.history;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.Nusave;
import seedu.address.model.budget.BudgetList;
import seedu.address.state.State;

public class VersionedNusave {
    private final Optional<Integer> budgetIndex;
    private final BudgetList budgetList;

    /**
     * Constructs a {@code VerisonedNusave}.
     * @param state the current state of NUSave
     * @param nusave the NUSave model to be versioned
     */
    public VersionedNusave(State state, Nusave nusave) {
        requireAllNonNull(state, nusave);
        this.budgetIndex = state.getBudgetIndex();
        this.budgetList = BudgetList.clone(nusave.getBudgetList());
    };

    public Optional<Integer> getBudgetIndex() {
        return budgetIndex;
    }

    public BudgetList getBudgetList() {
        return budgetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VersionedNusave that = (VersionedNusave) o;
        return Objects.equals(budgetIndex, that.budgetIndex)
                && Objects.equals(budgetList, that.budgetList);
    }
}
