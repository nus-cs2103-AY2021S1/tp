package seedu.bookmark.model.book.comparators;

import java.util.Comparator;

import seedu.bookmark.model.book.Book;

/**
 * Sorts {@code Book}s based on {@code PagesRead} in ascending order.
 */
public class BookPagesReadComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        int b1PagesRead = b1.getPagesRead();

        int b2PagesRead = b2.getPagesRead();

        return Integer.compare(b1PagesRead, b2PagesRead);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookPagesReadComparator); // instanceof handles nulls
    }

    @Override
    public String toString() {
        return "Pages Read (Bookmark)";
    }
}
