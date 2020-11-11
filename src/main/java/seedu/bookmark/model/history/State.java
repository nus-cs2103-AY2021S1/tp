package seedu.bookmark.model.history;

import java.util.List;
import java.util.function.Predicate;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.ReadOnlyUserPrefs;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;

/**
 * Represents the state of the {@code Model}
 */
public class State {

    private final ReadOnlyLibrary library;
    private final ReadOnlyUserPrefs userPrefs;
    /** {@code Predicate} to be applied to the {@code Book} stored in the {@code ReadOnlyLibrary} */
    private final Predicate<? super Book> filterPredicate;

    /**
     * Constructs a {@code State} with the given {@code ReadOnlyLibrary}, {@code ReadOnlyUserPrefs} and
     * {@code Predicate}
     */
    protected State(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs, Predicate<? super Book> filterPredicate) {
        this.library = library;
        this.userPrefs = userPrefs;
        this.filterPredicate = filterPredicate;
    }

    /**
     * Static method to create a {@code State} with the given {@code ReadOnlyLibrary}, {@code ReadOnlyUserPrefs} and
     * {@code Predicate}
     *
     * Create and uses copies of the arguments provided.
     */
    public static State createState(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs,
                                    Predicate<? super Book> filterPredicate) {
        ReadOnlyLibrary copiedLibrary = new Library(library);
        ReadOnlyUserPrefs copiedUserPrefs = new UserPrefs(userPrefs);
        return new State(copiedLibrary, copiedUserPrefs, filterPredicate);
    }

    /**
     * Returns the {@code ReadOnlyLibrary} stored in this {@code State}.
     */
    public ReadOnlyLibrary getLibrary() {
        return this.library;
    }

    /**
     * Returns the {@code ReadOnlyUserPrefs} stored in this {@code State}.
     */
    public ReadOnlyUserPrefs getUserPrefs() {
        return this.userPrefs;
    }

    /**
     * Returns the {@code Predicate} used stored in this {@code State}.
     */
    public Predicate<? super Book> getPredicate() {
        return this.filterPredicate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof State) {
            State otherState = (State) other;
            return this.library.equals(otherState.library)
                    && this.userPrefs.equals(otherState.userPrefs)
                    && this.filterPredicate.equals(otherState.filterPredicate)
                    && this.comparePredicate(otherState.filterPredicate);
        }
        return false;
    }

    /**
     * Returns whether a given predicate and the current predicate are considered equal when applied on the
     * same list of books.
     */
    private boolean comparePredicate(Predicate<? super Book> otherPredicate) {
        List<Book> books = library.getBookList();
        for (Book book : books) {
            if (this.filterPredicate.test(book) != otherPredicate.test(book)) {
                return false;
            }
        }
        return true;
    }
}
