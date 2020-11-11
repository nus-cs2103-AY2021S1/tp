package seedu.bookmark.model.book;

import static seedu.bookmark.commons.util.AppUtil.checkArgument;
import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Book's bookmark.
 * Guarantees: immutable; is valid as declared in {@link #isValidBookmark(String)}
 */
public class Bookmark {

    public static final String MESSAGE_CONSTRAINTS =
            "bookmark should only contain numeric characters, it should not be blank, and its numerical value should \n"
                    + "be less than or equals to the total number of pages in the book it is in.";

    /*
     * Only 1 to 9 number of digits from 0-9 allowed
     */
    public static final String VALIDATION_REGEX = "^\\d"
            + "{1," + TotalPages.MAX_TOTAL_PAGES_LENGTH + "}$";

    public final String value;

    /**
     * Constructs a default {@code Bookmark} at page 0.
     */
    public Bookmark() {
        this.value = "0";
    }

    /**
     * Constructs an {@code Bookmark}.
     *
     * @param bookmarkedPage A valid page string.
     */
    public Bookmark(String bookmarkedPage) {
        requireAllNonNull(bookmarkedPage);
        checkArgument(isValidBookmark(bookmarkedPage), MESSAGE_CONSTRAINTS);
        this.value = bookmarkedPage;
    }

    /**
     * Returns if a given string forms a valid bookmark.
     *
     * @param bookmarkedPage String representing the bookmark page
     * @return true iff bookmarkPage String is valid
     */
    public static boolean isValidBookmark(String bookmarkedPage) {
        if (!bookmarkedPage.matches(VALIDATION_REGEX)) {
            return false;
        }

        int bookmarkInt = Integer.parseInt(bookmarkedPage);
        return bookmarkInt >= 0;
    }


    /**
     * Returns if a given {@code Bookmark} is valid for a given {@code TotalPages}.
     *
     * @param bookmark bookmarked page
     * @param totalPages the total number of pages in the Book which the bookmark will be placed in
     * @return true iff bookmarked page is less than equals to total pages
     */
    public static boolean isValidBookmark(Bookmark bookmark, TotalPages totalPages) {
        int bookmarkPage = Integer.parseInt(bookmark.value);
        int totalPage = Integer.parseInt(totalPages.value);
        return bookmarkPage <= totalPage;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bookmark // instanceof handles nulls
                && value.equals(((Bookmark) other).value)); // state check
    }
}
