package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_1984;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.FULL_JANE_EYRE;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;

import org.junit.jupiter.api.Test;

import seedu.bookmark.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getTags().remove(0));
    }

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(HARRY_POTTER.isSameBook(HARRY_POTTER));

        // different name and genre -> returns false
        assertFalse(HARRY_POTTER.isSameBook(FULL_JANE_EYRE));

        // different name -> returns false
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withName(VALID_NAME_1984).build();
        assertFalse(HARRY_POTTER.isSameBook(editedHarryPotter));

        // different genre -> returns false
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withGenre(VALID_GENRE_1984).build();
        assertFalse(HARRY_POTTER.isSameBook(editedHarryPotter));

        // different total pages -> returns true
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withTotalPages(VALID_TOTAL_PAGES_1984).build();
        assertTrue(HARRY_POTTER.isSameBook(editedHarryPotter));

        // different bookmarks -> returns true
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withBookmark(VALID_BOOKMARK_1984).build();
        assertTrue(HARRY_POTTER.isSameBook(editedHarryPotter));

        // same values, different tags -> returns true
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_BAD).build();
        assertTrue(HARRY_POTTER.isSameBook(editedHarryPotter));

        // null -> returns false
        assertFalse(HARRY_POTTER.isSameBook(null));
    }

    @Test
    public void getFieldValues_validFields_correctValues() {
        final String totalPages = "1500";
        final String bookmark = "50";
        final int totalPagesValue = 1500;
        final int bookmarkValue = 50;

        Book testBook = new BookBuilder()
                .withName("TEST")
                .withGenre("FICTION")
                .withTotalPages(totalPages)
                .withBookmark(bookmark)
                .build();

        testBook = Book.setGoal(testBook, new Goal("5 15/12/2024"));

        assertTrue(testBook.hasStartedReading());
        assertTrue(testBook.hasGoal());
        assertEquals((bookmarkValue), testBook.getPagesRead());
        assertEquals((totalPagesValue), testBook.getTotalPagesNumber());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book harryPotterCopy = new BookBuilder(HARRY_POTTER).build();
        assertTrue(HARRY_POTTER.equals(harryPotterCopy));

        // same object -> returns true
        assertTrue(HARRY_POTTER.equals(HARRY_POTTER));

        // null -> returns false
        assertFalse(HARRY_POTTER.equals(null));

        // different type -> returns false
        assertFalse(HARRY_POTTER.equals(5));

        // different book -> returns false
        assertFalse(HARRY_POTTER.equals(FULL_JANE_EYRE));

        // different name -> returns false
        Book editedHarryPotter = new BookBuilder(HARRY_POTTER).withName(VALID_NAME_JANE_EYRE).build();
        assertFalse(HARRY_POTTER.equals(editedHarryPotter));

        // different genre -> returns false
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withGenre(VALID_GENRE_1984).build();
        assertFalse(HARRY_POTTER.equals(editedHarryPotter));

        // different total pages -> returns false
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withTotalPages(VALID_TOTAL_PAGES_1984).build();
        assertFalse(HARRY_POTTER.equals(editedHarryPotter));

        // different bookmark -> returns false
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withBookmark(VALID_BOOKMARK_1984).build();
        assertFalse(HARRY_POTTER.equals(editedHarryPotter));

        // different tags -> returns false
        editedHarryPotter = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_BAD).build();
        assertFalse(HARRY_POTTER.equals(editedHarryPotter));
    }
}
