package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NOTE_TITLE;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.BookBuilder;

/**
 * Contains unit tests for DeleteNoteCommand.
 */

public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_deleteValidNoteUnfilteredList_success() {
        Book baseBook = model.getFilteredBookList().get(0);
        Book deleteNoteFromBook = new BookBuilder(baseBook).withNotes(VALID_NOTE_TITLE).build();
        Book editedBook = new BookBuilder(baseBook).build();
        DeleteNoteCommand deleteNoteCommand =
                new DeleteNoteCommand(INDEX_FIRST_BOOK, Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                VALID_NOTE_TITLE, editedBook.getName());

        Model beforeDeletionModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());

        beforeDeletionModel.setBook(model.getFilteredBookList().get(0), deleteNoteFromBook);
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        CommandResult expectedResult = new CommandResult(expectedMessage, false, false,
                ViewType.MOST_RECENTLY_USED);
        assertCommandSuccess(deleteNoteCommand, beforeDeletionModel, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidBookIndexInvalidNoteIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        DeleteNoteCommand deleteNoteCommand =
                new DeleteNoteCommand(outOfBoundIndex, Index.fromOneBased(1));

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test void execute_validBookIndexInvalidNoteIndexUnfilteredList_failure() {
        Index outOfBoundNoteIndex = Index.fromOneBased(1);
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_BOOK, outOfBoundNoteIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of library
     */
    @Test
    public void execute_invalidBookIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of the library book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex,
               Index.fromOneBased(1));

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validBookIndexInvalidNoteIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundNoteIndex = Index.fromOneBased(1);

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_BOOK,
                Index.fromOneBased(1));

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteNoteCommand standardCommand = new DeleteNoteCommand(INDEX_FIRST_BOOK, Index.fromOneBased(1));

        // same values -> returns true
        DeleteNoteCommand commandWithSameValues = new DeleteNoteCommand(INDEX_FIRST_BOOK, Index.fromOneBased(1));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteNoteCommand(INDEX_SECOND_BOOK, Index.fromOneBased(1))));

        // different note index -> returns false
        assertFalse(standardCommand.equals(new DeleteNoteCommand(INDEX_FIRST_BOOK, Index.fromOneBased(2))));
    }

}
