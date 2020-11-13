package seedu.address.model.sort;

import java.util.Comparator;

import seedu.address.model.budget.Budget;

public class SortBudgetsByCreateDate implements Comparator<Budget> {

    @Override
    public int compare(Budget a, Budget b) {
        return b.getCreatedOn().compareTo(a.getCreatedOn());
    }
}
