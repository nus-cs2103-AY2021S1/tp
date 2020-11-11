package seedu.bookmark.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.UniqueBookList;

/**
 * Wraps all data at the bookmark level
 * Duplicates are not allowed (by .isSameBook comparison)
 */
public class Library implements ReadOnlyLibrary {

    protected final UniqueBookList books;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        books = new UniqueBookList();
    }

    public Library() {}

    /**
     * Creates an Library using the Books in the {@code toBeCopied}
     */
    public Library(ReadOnlyLibrary toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the book list with {@code books}.
     * {@code books} must not contain duplicate books.
     */
    protected void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Resets the existing data of this {@code Library} with {@code newData}.
     */
    public void resetData(ReadOnlyLibrary newData) {
        requireNonNull(newData);

        setBooks(newData.getBookList());
    }

    /**
     * Sorts the data of this {@code Library} according to the input {@code comparator}.
     */
    public void sortBooks(Comparator<Book> comparator) {
        books.sortBookList(comparator);
    }

    //// book-level operations

    /**
     * Returns true if a book with the same identity as {@code book} exists in the library.
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Returns the number of books stored in this {@code Library}.
     */
    public int getSize() {
        return books.getSize();
    }

    /**
     * Adds a book to the library.
     * The book must not already exist in the library.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Replaces the given book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the library.
     * The book identity of {@code editedBook} must not be the same as another existing book in the library.
     */
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        books.setBook(target, editedBook);
    }

    /**
     * Removes {@code key} from this {@code Library}.
     * {@code key} must exist in the library.
     */
    public void removeBook(Book key) {
        books.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return books.asUnmodifiableObservableList().size() + " books";
        // TODO: refine later
    }

    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Library // instanceof handles nulls
                && books.equals(((Library) other).books));
    }

    @Override
    public int hashCode() {
        return books.hashCode();
    }
}
