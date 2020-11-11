package seedu.bookmark.model;

import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;

/**
 * Unmodifiable view of a Library
 */
public interface ReadOnlyLibrary {

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Book> getBookList();

}
