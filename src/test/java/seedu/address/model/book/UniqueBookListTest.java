package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK1;
import static seedu.address.testutil.TypicalBooks.BOOK9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.book.exceptions.BookNotFoundException;
import seedu.address.model.book.exceptions.DuplicateBookException;
import seedu.address.testutil.BookBuilder;

public class UniqueBookListTest {

    private final UniqueBookList uniqueBookList = new UniqueBookList();

    @Test
    public void contains_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.contains(null));
    }

    @Test
    public void contains_bookNotInList_returnsFalse() {
        assertFalse(uniqueBookList.contains(BOOK1));
    }

    @Test
    public void contains_bookInList_returnsTrue() {
        uniqueBookList.add(BOOK1);
        assertTrue(uniqueBookList.contains(BOOK1));
    }

    @Test
    public void contains_bookWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookList.add(BOOK1);
        Book editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();
        assertTrue(uniqueBookList.contains(editedAlice));
    }

    @Test
    public void add_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.add(null));
    }

    @Test
    public void add_duplicateBook_throwsDuplicateBookException() {
        uniqueBookList.add(BOOK1);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.add(BOOK1));
    }

    @Test
    public void setBook_nullTargetBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(null, BOOK1));
    }

    @Test
    public void setBook_nullEditedBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(BOOK1, null));
    }

    @Test
    public void setBook_targetBookNotInList_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.setBook(BOOK1, BOOK1));
    }

    @Test
    public void setBook_editedBookIsSameBook_success() {
        uniqueBookList.add(BOOK1);
        uniqueBookList.setBook(BOOK1, BOOK1);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOOK1);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasSameIdentity_success() {
        uniqueBookList.add(BOOK1);
        Book editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();
        uniqueBookList.setBook(BOOK1, editedAlice);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(editedAlice);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasDifferentIdentity_success() {
        uniqueBookList.add(BOOK1);
        uniqueBookList.setBook(BOOK1, BOOK9);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOOK9);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasNonUniqueIdentity_throwsDuplicateBookException() {
        uniqueBookList.add(BOOK1);
        uniqueBookList.add(BOOK9);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBook(BOOK1, BOOK9));
    }

    @Test
    public void remove_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.remove(null));
    }

    @Test
    public void remove_bookDoesNotExist_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.remove(BOOK1));
    }

    @Test
    public void remove_existingBook_removesBook() {
        uniqueBookList.add(BOOK1);
        uniqueBookList.remove(BOOK1);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullUniqueBookList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((UniqueBookList) null));
    }

    @Test
    public void setBooks_uniqueBookList_replacesOwnListWithProvidedUniqueBookList() {
        uniqueBookList.add(BOOK1);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOOK9);
        uniqueBookList.setBooks(expectedUniqueBookList);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((List<Book>) null));
    }

    @Test
    public void setBooks_list_replacesOwnListWithProvidedList() {
        uniqueBookList.add(BOOK1);
        List<Book> bookList = Collections.singletonList(BOOK9);
        uniqueBookList.setBooks(bookList);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOOK9);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_listWithDuplicateBooks_throwsDuplicateBookException() {
        List<Book> listWithDuplicateBooks = Arrays.asList(BOOK1, BOOK1);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBooks(listWithDuplicateBooks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookList.asUnmodifiableObservableList().remove(0));
    }
}
