package seedu.expense.model.expense;

/**
 * Compares two expenses according to their alphabetical description
 */
public class AmountComparator extends SortKeyComparator {
    public static final String SORT_KEYWORD = "amount";

    /**
     * Constructor for {@code DescriptionComparator} to work with SortCommand.
     * @param sortActive specifies if the sort should be used.
     * @param reverse specifies if the sort should be reversed.
     */
    public AmountComparator(boolean sortActive, boolean reverse, int index) {
        super(sortActive, reverse, index);
    }

    @Override
    public int compare(Expense o1, Expense o2) {
        int s1 = o1.getAmount().value;
        int s2 = o2.getAmount().value;
        return s1 - s2;
    }

    @Override
    public String toString() {
        return this.isReverse() ? this.SORT_KEYWORD + " reversed" : this.SORT_KEYWORD;
    }
}
