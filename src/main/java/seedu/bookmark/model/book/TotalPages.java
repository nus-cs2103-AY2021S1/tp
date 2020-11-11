package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents the total number of pages in a Book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTotalPages(String)}
 */
public class TotalPages {

    public static final int MAX_TOTAL_PAGES_LENGTH = 9;

    public static final String MESSAGE_CONSTRAINTS =
            "Total pages should only contain numeric characters, it should not be blank "
                    + "and its numerical value should be greater than 0. \n"
                    + String.format("Maximum of %d digits allowed.", MAX_TOTAL_PAGES_LENGTH);

    /*
     * Only 1 to 9 number of digits from 0-9 allowed
     */
    public static final String VALIDATION_REGEX = "^\\d"
            + "{1," + MAX_TOTAL_PAGES_LENGTH + "}$";

    public final String value;

    /**
     * Constructs an {@code TotalPages}.
     *
     * @param totalPages A valid total pages string.
     */
    public TotalPages(String totalPages) {
        requireNonNull(totalPages);
        checkArgument(isValidTotalPages(totalPages), MESSAGE_CONSTRAINTS);
        value = totalPages;
    }

    /**
     * Returns if a given string is a valid total pages.
     */
    public static boolean isValidTotalPages(String test) {
        return test.matches(VALIDATION_REGEX) && Integer.parseInt(test) > 0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TotalPages // instanceof handles nulls
                && value.equals(((TotalPages) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
