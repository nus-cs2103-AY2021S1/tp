package seedu.expense.model.expense;

import java.time.LocalDate;

/**
 * Compares two expenses according to their alphabetical description
 */
public class DateComparator extends SortKeyComparator {
    public static final String SORT_KEYWORD = "date";

    /**
     * Constructor for {@code DescriptionComparator} to work with SortCommand.
     * @param sortActive specifies if the sort should be used.
     * @param reverse specifies if the sort should be reversed.
     */
    public DateComparator(boolean sortActive, boolean reverse, int index) {
        super(sortActive, reverse, index);
    }

    @Override
    public int compare(Expense o1, Expense o2) {
        LocalDate s1 = o1.getDate().value;
        LocalDate s2 = o2.getDate().value;
        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return this.isReverse() ? this.SORT_KEYWORD + " reversed" : this.SORT_KEYWORD;
    }
}
