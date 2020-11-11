package seedu.bookmark.model.book.comparators;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_READING_PROGRESS;

import java.util.Comparator;

import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.model.book.Book;

public class ComparatorGenerator {
    /**
     * Returns a comparator based on the input prefix
     * @return Comparator based on input prefix
     */
    public static Comparator<Book> comparatorGenerator(Prefix inputPrefix) {
        Comparator<Book> comparator = null;
        if (inputPrefix == PREFIX_NAME) {
            comparator = new BookNameComparator();
        } else if (inputPrefix == PREFIX_GENRE) {
            comparator = new BookGenreComparator();
        } else if (inputPrefix == PREFIX_BOOKMARK) {
            comparator = new BookPagesReadComparator();
        } else if (inputPrefix == PREFIX_READING_PROGRESS) {
            comparator = new BookReadingProgressComparator();
        }
        return comparator;
    }
}
