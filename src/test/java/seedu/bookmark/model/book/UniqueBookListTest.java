package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.FULL_JANE_EYRE;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.book.exceptions.BookNotFoundException;
import seedu.bookmark.model.book.exceptions.DuplicateBookException;
import seedu.bookmark.testutil.BookBuilder;

public class UniqueBookListTest {

    private final UniqueBookList uniqueBookList = new UniqueBookList();

    @Test
    public void contains_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.contains(null));
    }

    @Test
    public void contains_bookNotInList_returnsFalse() {
        assertFalse(uniqueBookList.contains(HARRY_POTTER));
    }

    @Test
    public void contains_bookInList_returnsTrue() {
        uniqueBookList.add(HARRY_POTTER);
        assertTrue(uniqueBookList.contains(HARRY_POTTER));
    }

    @Test
    public void contains_bookWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookList.add(HARRY_POTTER);
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_GOOD)
                .build();
        assertTrue(uniqueBookList.contains(editedHarryPotter));
    }

    @Test
    public void add_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.add(null));
    }

    @Test
    public void add_duplicateBook_throwsDuplicateBookException() {
        uniqueBookList.add(HARRY_POTTER);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.add(HARRY_POTTER));
    }

    @Test
    public void setBook_nullTargetBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(null, HARRY_POTTER));
    }

    @Test
    public void setBook_nullEditedBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(HARRY_POTTER, null));
    }

    @Test
    public void setBook_targetBookNotInList_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.setBook(HARRY_POTTER, HARRY_POTTER));
    }

    @Test
    public void setBook_editedBookIsSameBook_success() {
        uniqueBookList.add(HARRY_POTTER);
        uniqueBookList.setBook(HARRY_POTTER, HARRY_POTTER);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(HARRY_POTTER);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasSameIdentity_success() {
        uniqueBookList.add(HARRY_POTTER);
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_GOOD)
                .build();
        uniqueBookList.setBook(HARRY_POTTER, editedHarryPotter);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(editedHarryPotter);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasDifferentIdentity_success() {
        uniqueBookList.add(HARRY_POTTER);
        uniqueBookList.setBook(HARRY_POTTER, FULL_JANE_EYRE);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(FULL_JANE_EYRE);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasNonUniqueIdentity_throwsDuplicateBookException() {
        uniqueBookList.add(HARRY_POTTER);
        uniqueBookList.add(FULL_JANE_EYRE);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBook(HARRY_POTTER, FULL_JANE_EYRE));
    }

    @Test
    public void remove_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.remove(null));
    }

    @Test
    public void remove_bookDoesNotExist_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.remove(HARRY_POTTER));
    }

    @Test
    public void remove_existingBook_removesBook() {
        uniqueBookList.add(HARRY_POTTER);
        uniqueBookList.remove(HARRY_POTTER);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullUniqueBookList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((UniqueBookList) null));
    }

    @Test
    public void setBooks_uniqueBookList_replacesOwnListWithProvidedUniqueBookList() {
        uniqueBookList.add(HARRY_POTTER);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(FULL_JANE_EYRE);
        uniqueBookList.setBooks(expectedUniqueBookList);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((List<Book>) null));
    }

    @Test
    public void setBooks_list_replacesOwnListWithProvidedList() {
        uniqueBookList.add(HARRY_POTTER);
        List<Book> bookList = Collections.singletonList(FULL_JANE_EYRE);
        uniqueBookList.setBooks(bookList);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(FULL_JANE_EYRE);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_listWithDuplicateBooks_throwsDuplicateBookException() {
        List<Book> listWithDuplicateBooks = Arrays.asList(HARRY_POTTER, HARRY_POTTER);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBooks(listWithDuplicateBooks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookList.asUnmodifiableObservableList().remove(0));
    }
}
