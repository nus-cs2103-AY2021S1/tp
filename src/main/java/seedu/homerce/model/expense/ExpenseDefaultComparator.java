package seedu.homerce.model.expense;

import java.util.Comparator;

public class ExpenseDefaultComparator implements Comparator<Expense> {
    @Override
    public int compare(Expense t1, Expense t2) {
        if (t1.getDate().getLocalDate().isBefore(t2.getDate().getLocalDate())) {
            return 1;
        } else if (t1.getDate().getLocalDate().isAfter(t2.getDate().getLocalDate())) {
            return -1;
        } else {
            return 0;
        }
    }
}
