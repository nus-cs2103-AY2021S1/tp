package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NOTE_TEXT;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NOTE_TITLE;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.testutil.BookBuilder;

/**
 * Contains unit tests for AddNoteCommand.
 */

public class AddNoteCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_addValidNoteUnfilteredList_success() {
        Note noteToAdd = new Note(VALID_NOTE_TITLE, VALID_NOTE_TITLE);
        Book toAddNote = model.getFilteredBookList().get(0);
        Book editedBook = new BookBuilder(toAddNote).withNotes(VALID_NOTE_TITLE).build();
        AddNoteCommand addNoteCommand =
                new AddNoteCommand(INDEX_FIRST_BOOK, noteToAdd);

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                noteToAdd.title, editedBook.getName());

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        CommandResult expectedResult = new CommandResult(expectedMessage, false, false,
                ViewType.MOST_RECENTLY_USED);
        assertCommandSuccess(addNoteCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicateNoteUnfilteredList_failure() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book bookWithNote = new BookBuilder(firstBook).withNotes("Chapter 1").build();
        Model modifiedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        modifiedModel.setBook(firstBook, bookWithNote);
        AddNoteCommand addNoteCommand = new AddNoteCommand(INDEX_FIRST_BOOK, new Note("Chapter 1", "Chapter 1"));

        assertCommandFailure(addNoteCommand, modifiedModel, AddNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }

    @Test
    public void execute_tooManyNotesUnfilteredList_failure() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        BookBuilder builder = new BookBuilder(firstBook);
        List<String> notes = new ArrayList<>();
        for (int i = 0; i <= Book.MAX_NOTE_COUNT; i++) {
            notes.add(String.valueOf(i));
        }
        // booktoAddNotes has the valid max number of notes
        Book bookToAddNotes = builder.withNotes(notes.toArray(new String[]{})).build();
        Model modifiedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        modifiedModel.setBook(firstBook, bookToAddNotes);
        AddNoteCommand addNoteCommand = new AddNoteCommand(INDEX_FIRST_BOOK, new Note("TEST", "TEST"));

        assertCommandFailure(addNoteCommand, modifiedModel, String.format(Messages.MESSAGE_TOO_MANY_NOTES,
                Book.MAX_NOTE_COUNT));
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        AddNoteCommand addNoteCommand =
                new AddNoteCommand(outOfBoundIndex, new Note(VALID_NOTE_TITLE, VALID_NOTE_TEXT));

        assertCommandFailure(addNoteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
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

        AddNoteCommand addNoteCommand = new AddNoteCommand(outOfBoundIndex,
                new Note(VALID_NOTE_TITLE, VALID_NOTE_TEXT));

        assertCommandFailure(addNoteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Note validNote = new Note(VALID_NOTE_TITLE, VALID_NOTE_TEXT);
        final AddNoteCommand standardCommand = new AddNoteCommand(INDEX_FIRST_BOOK, validNote);

        // same values -> returns true
        AddNoteCommand commandWithSameValues = new AddNoteCommand(INDEX_FIRST_BOOK, validNote);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(INDEX_SECOND_BOOK, validNote)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(INDEX_FIRST_BOOK, new Note("Hello", "World"))));
    }

}
