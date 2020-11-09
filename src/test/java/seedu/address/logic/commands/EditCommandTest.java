package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditBookDescriptor;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.testutil.BookBuilder;
import seedu.address.testutil.EditBookDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBook = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastBook.getZeroBased());

        BookBuilder bookInList = new BookBuilder(lastBook);
        Book editedBook = bookInList.withName(VALID_NAME_BOOK2).withIsbn(VALID_ISBN_BOOK2)
                .withCategories(VALID_CATEGORY_MATH).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK2)
                .withIsbn(VALID_ISBN_BOOK2).withCategories(VALID_CATEGORY_MATH).build();
        EditCommand editCommand = new EditCommand(indexLastBook, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(lastBook, editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, new EditBookDescriptor());
        Book editedBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book bookInFilteredList = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(bookInFilteredList).withName(VALID_NAME_BOOK2).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK2).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBookUnfilteredList_failure() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(firstBook).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_BOOK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_duplicateBookFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        // edit book in filtered list into a duplicate in library
        Book bookInList = model.getLibrary().getBookList().get(INDEX_SECOND_BOOK.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder(bookInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK2).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of library
     */
    @Test
    public void execute_invalidBookIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK2).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_BOOK, DESC_BOOK1);

        // same values -> returns true
        EditCommand.EditBookDescriptor copyDescriptor = new EditCommand.EditBookDescriptor(DESC_BOOK1);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_BOOK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_BOOK, DESC_BOOK1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_BOOK, DESC_BOOK2)));
    }

}
