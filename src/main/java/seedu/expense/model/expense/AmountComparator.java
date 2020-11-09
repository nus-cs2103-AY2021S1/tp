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
        return o1.getAmount().compareTo(o2.getAmount());
    }

    @Override
    public String toString() {
        return this.isReverse() ? this.SORT_KEYWORD + " reversed" : this.SORT_KEYWORD;
    }
}
