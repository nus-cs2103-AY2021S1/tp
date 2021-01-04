package seedu.homerce.model.expense;

import java.util.Comparator;

public class ExpenseComparator implements Comparator<Expense> {
    @Override
    public int compare(Expense t1, Expense t2) {
        return t1.getValue().value.compareTo(t2.getValue().value);
    }
}
