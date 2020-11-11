package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_JANE_EYRE;
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
import seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.testutil.BookBuilder;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */

public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Book editedBook = new BookBuilder().build();
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(editedBook).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        CommandResult expectedResult = new CommandResult(expectedMessage, false , false,
                ViewType.MOST_RECENTLY_USED);
        assertCommandSuccess(editCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBook = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastBook.getZeroBased());

        BookBuilder bookInList = new BookBuilder(lastBook);
        Book editedBook = bookInList.withName(VALID_NAME_JANE_EYRE)
                .withTags(VALID_TAG_GOOD).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE)
                .withTags(VALID_TAG_GOOD).build();
        EditCommand editCommand = new EditCommand(indexLastBook, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(lastBook, editedBook);

        CommandResult expectedResult = new CommandResult(expectedMessage, false , false,
                ViewType.MOST_RECENTLY_USED);

        assertCommandSuccess(editCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, new EditBookDescriptor());
        Book editedBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());

        CommandResult expectedResult = new CommandResult(expectedMessage, false , false,
                ViewType.MOST_RECENTLY_USED);

        assertCommandSuccess(editCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book bookInFilteredList = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(bookInFilteredList).withName(VALID_NAME_JANE_EYRE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);
        model.updateFilteredBookList(b -> true);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);
        expectedModel.updateFilteredBookList(b -> true);

        CommandResult expectedResult = new CommandResult(expectedMessage, false , false,
                ViewType.MOST_RECENTLY_USED);
        assertCommandSuccess(editCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicateBookUnfilteredList_failure() {
        seedu.bookmark.model.book.Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor descriptor =
                new seedu.bookmark.testutil.EditBookDescriptorBuilder(firstBook).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_BOOK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_duplicateBookFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        // edit book in filtered list into a duplicate in address book
        Book bookInList = model.getLibrary().getBookList().get(INDEX_SECOND_BOOK.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder(bookInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build();
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
        // ensures that outOfBoundIndex is still in bounds of the library book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validBookmark_success() {
        Book editedBook = new BookBuilder()
                                  .withName(VALID_NAME_JANE_EYRE)
                                  .withGenre(VALID_GENRE_JANE_EYRE)
                                  .withTags(VALID_TAG_GOOD)
                                  .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE)
                                  .withBookmark(VALID_TOTAL_PAGES_JANE_EYRE).build();
        Book oldBook = new BookBuilder()
                                  .withName(VALID_NAME_JANE_EYRE)
                                  .withGenre(VALID_GENRE_JANE_EYRE)
                                  .withTags(VALID_TAG_GOOD)
                                  .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE)
                                  .withBookmark(VALID_BOOKMARK_JANE_EYRE).build();

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(oldBook)
                                                            .withBookmark(VALID_TOTAL_PAGES_JANE_EYRE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        CommandResult expectedResult = new CommandResult(expectedMessage, false , false,
                ViewType.MOST_RECENTLY_USED);

        assertCommandSuccess(editCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidOutOfRangeBookmark_failure() {

        Book oldBook = new BookBuilder()
                               .withName(VALID_NAME_JANE_EYRE)
                               .withGenre(VALID_GENRE_JANE_EYRE)
                               .withTags(VALID_TAG_GOOD)
                               .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE)
                               .withBookmark(VALID_BOOKMARK_JANE_EYRE).build();

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(oldBook)
                                                            .withBookmark(VALID_TOTAL_PAGES_JANE_EYRE + "0").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, descriptor);

        assertCommandFailure(editCommand, model, Bookmark.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_BOOK, DESC_1984);

        // same values -> returns true
        EditBookDescriptor copyDescriptor = new EditBookDescriptor(DESC_1984);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_BOOK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_BOOK, DESC_1984)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_BOOK, DESC_JANE_EYRE)));
    }

}
