package seedu.bookmark.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.history.State;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;
    int MAX_BOOK_CAPACITY = 100;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' bookmark file path.
     */
    Path getBookmarkFilePath();

    /**
     * Sets the user prefs' bookmark file path.
     */
    void setBookmarkFilePath(Path bookMarkFilePath);

    /**
     * Sets the user prefs' sorting preference.
     */
    void setSortingPreference(String newSortingPreference);

    /**
     * Returns the user pref's sorting preference.
     */
    String getSortingPreference();

    /**
     * Replaces bookmark data with the data in {@code library}.
     */
    void setLibrary(ReadOnlyLibrary library);

    /** Returns the Library */
    ReadOnlyLibrary getLibrary();

    /** Returns the EditDistance */
    WordBank getWordBank();

    /**
     * Returns true if a book with the same identity as {@code book} exists in the Library.
     */
    boolean hasBook(Book book);

    /**
     * Returns true if the {@code Model} is storing the maximum number of books already.
     */
    boolean isFullCapacity();

    /**
     * Deletes the given book.
     * The book must exist in the library.
     */
    void deleteBook(Book target);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the library.
     */
    void addBook(Book book);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in the library.
     * The book identity of {@code editedBook} must not be the same as another existing book in the library.
     */
    void setBook(Book target, Book editedBook);

    /** Returns an unmodifiable view of the filtered book list */
    ObservableList<Book> getFilteredBookList();

    /**
     * Undoes the most recent change that modified the books stored in the {@code Model}.
     */
    void undo();

    /**
     * Redoes the most recently undone change that modified the books stored in the {@code Model}.
     */
    void redo();

    /**
     * Save the current state of the {@code Model}
     */
    void save();

    State getCurrentState();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate);

    /**
     * Updates the comparator of the filtered book list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredBookList(Comparator<Book> comparator);

    void sortByDefaultComparator();
}
