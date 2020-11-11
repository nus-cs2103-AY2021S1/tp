package seedu.bookmark.model.book.comparators;

import java.util.Comparator;

import seedu.bookmark.model.book.Book;

/**
 * Sorts {@code Book}s based on {@code ReadingProgress} in ascending order.
 */
public class BookReadingProgressComparator implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {

        float b1PagesRead = b1.getPagesRead();
        float b1TotalPages = b1.getTotalPagesNumber();
        float b1ReadingProgress = b1PagesRead / b1TotalPages;

        float b2PagesRead = b2.getPagesRead();
        float b2TotalPages = b2.getTotalPagesNumber();
        float b2ReadingProgress = b2PagesRead / b2TotalPages;

        return Float.compare(b1ReadingProgress, b2ReadingProgress);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookReadingProgressComparator); // instanceof handles nulls
    }

    @Override
    public String toString() {
        return "Reading Progress";
    }
}
