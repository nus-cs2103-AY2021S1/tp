package seedu.expense.model.expense;

/**
 * Compares two expenses according to their alphabetical description
 */
public class DescriptionComparator extends SortKeyComparator {
    public static final String SORT_KEYWORD = "description";

    /**
     * Constructor for {@code DescriptionComparator} to work with SortCommand.
     * @param sortActive specifies if the sort should be used.
     * @param reverse specifies if the sort should be reversed.
     */
    public DescriptionComparator(boolean sortActive, boolean reverse, int index) {
        super(sortActive, reverse, index);
    }

    @Override
    public int compare(Expense o1, Expense o2) {
        String s1 = o1.getDescription().toString();
        String s2 = o2.getDescription().toString();

        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return this.isReverse() ? this.SORT_KEYWORD + " reversed" : this.SORT_KEYWORD;
    }

}
