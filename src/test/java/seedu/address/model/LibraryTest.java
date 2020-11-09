package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK1;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;
import seedu.address.testutil.BookBuilder;

public class LibraryTest {

    private final Library library = new Library();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), library.getBookList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLibrary_replacesData() {
        Library newData = getTypicalLibrary();
        library.resetData(newData);
        assertEquals(newData, library);
    }


    @Test
    public void hasBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasBook(null));
    }

    @Test
    public void hasBook_bookNotInLibrary_returnsFalse() {
        assertFalse(library.hasBook(BOOK1));
    }

    @Test
    public void hasBook_bookInLibrary_returnsTrue() {
        library.addBook(BOOK1);
        assertTrue(library.hasBook(BOOK1));
    }

    @Test
    public void hasBook_bookWithSameIdentityFieldsInLibrary_returnsTrue() {
        library.addBook(BOOK1);
        Book editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();
        assertTrue(library.hasBook(editedAlice));
    }

    @Test
    public void getBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getBookList().remove(0));
    }

    /**
     * A stub ReadOnlyLibrary whose books list can violate interface constraints.
     */
    private static class LibraryStub implements ReadOnlyLibrary {
        private final ObservableList<Book> books = FXCollections.observableArrayList();

        LibraryStub(Collection<Book> books) {
            this.books.setAll(books);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }

        @Override
        public ObservableList<Problem> getProblemList() {
            return getProblemList();
        }
    }

}
