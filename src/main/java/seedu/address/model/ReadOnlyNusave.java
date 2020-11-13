package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.budget.Budget;

public interface ReadOnlyNusave {
    ObservableList<Budget> getBudgetListAsObservableList();
}
