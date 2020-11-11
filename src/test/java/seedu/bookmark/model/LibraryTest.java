package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.exceptions.DuplicateBookException;
import seedu.bookmark.testutil.BookBuilder;

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
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Library newData = getTypicalLibrary();
        library.resetData(newData);
        assertEquals(newData, library);
    }

    @Test
    public void resetData_withDuplicateBooks_throwsDuplicateBookException() {
        // Two books with the same identity fields
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_GOOD)
                .build();
        List<Book> newBooks = Arrays.asList(HARRY_POTTER, editedHarryPotter);
        LibraryStub newData = new LibraryStub(newBooks);

        assertThrows(DuplicateBookException.class, () -> library.resetData(newData));
    }

    @Test
    public void hasBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasBook(null));
    }

    @Test
    public void hasBook_bookNotInAddressBook_returnsFalse() {
        assertFalse(library.hasBook(HARRY_POTTER));
    }

    @Test
    public void hasBook_bookInAddressBook_returnsTrue() {
        library.addBook(HARRY_POTTER);
        assertTrue(library.hasBook(HARRY_POTTER));
    }

    @Test
    public void hasBook_bookWithSameIdentityFieldsInAddressBook_returnsTrue() {
        library.addBook(HARRY_POTTER);
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_GOOD)
                .build();
        assertTrue(library.hasBook(editedHarryPotter));
    }

    @Test
    public void getBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getBookList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose books list can violate interface constraints.
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
    }

}
