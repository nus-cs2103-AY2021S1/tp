package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.testutil.TypicalFlashcards;
import quickcache.testutil.TypicalIndexes;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToDelete =
            model.getFilteredFlashcardList().get(TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);

        ModelManager expectedModel = new ModelManager(model.getQuickCache(), new UserPrefs());
        expectedModel.deleteFlashcard(flashcardToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        Flashcard flashcardToDelete =
            model.getFilteredFlashcardList().get(TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);

        Model expectedModel = new ModelManager(model.getQuickCache(), new UserPrefs());
        expectedModel.deleteFlashcard(flashcardToDelete);
        showNoFlashcard(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different delete command -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashcard(Model model) {
        model.updateFilteredFlashcardList(p -> false);

        assertTrue(model.getFilteredFlashcardList().isEmpty());
    }
}
