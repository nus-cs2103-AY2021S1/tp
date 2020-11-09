package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK1;
import static seedu.address.testutil.TypicalBooks.BOOK9;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getCategories().remove(0));
    }

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(BOOK1.isSameBook(BOOK1));

        // null -> returns false
        assertFalse(BOOK1.isSameBook(null));

        // different isbn and email -> returns false
        Book editedAlice = new BookBuilder(BOOK1).withIsbn(VALID_ISBN_BOOK2).withEmail(VALID_EMAIL_BOOK2).build();
        assertFalse(BOOK1.isSameBook(editedAlice));


        // same name, same isbn, different attributes -> returns true
        editedAlice = new BookBuilder(BOOK1).withEmail(VALID_EMAIL_BOOK2).withLanguage(VALID_LANGUAGE_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();
        assertTrue(BOOK1.isSameBook(editedAlice));


        // same name, same isbn, same email, different attributes -> returns true
        editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();
        assertTrue(BOOK1.isSameBook(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book aliceCopy = new BookBuilder(BOOK1).build();
        assertTrue(BOOK1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(BOOK1.equals(BOOK1));

        // null -> returns false
        assertFalse(BOOK1.equals(null));

        // different type -> returns false
        assertFalse(BOOK1.equals(5));

        // different book -> returns false
        assertFalse(BOOK1.equals(BOOK9));

        // different name -> returns false
        Book editedAlice = new BookBuilder(BOOK1).withName(VALID_NAME_BOOK2).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different isbn -> returns false
        editedAlice = new BookBuilder(BOOK1).withIsbn(VALID_ISBN_BOOK2).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BookBuilder(BOOK1).withEmail(VALID_EMAIL_BOOK2).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different language -> returns false
        editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOOK2).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different categories -> returns false
        editedAlice = new BookBuilder(BOOK1).withCategories(VALID_CATEGORY_MATH).build();
        assertFalse(BOOK1.equals(editedAlice));
    }
}
