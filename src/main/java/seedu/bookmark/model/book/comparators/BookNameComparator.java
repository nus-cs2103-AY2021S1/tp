package seedu.bookmark.model.book.comparators;

import java.util.Comparator;

import seedu.bookmark.model.book.Book;

/**
 * Sorts {@code Book}s based on {@code Name} in lexicographical order.
 */
public class BookNameComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        String b1name = b1.getName().toString().toLowerCase();
        String b2name = b2.getName().toString().toLowerCase();
        return b1name.compareTo(b2name);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookNameComparator); // instanceof handles nulls
    }

    @Override
    public String toString() {
        return "Name";
    }
}
