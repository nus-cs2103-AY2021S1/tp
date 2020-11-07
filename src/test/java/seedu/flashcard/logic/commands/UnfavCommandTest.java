package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for UnfavCommand.
 */
public class UnfavCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToUnfavourite = model.getFilteredFlashcardList().get(INDEX_SECOND_FLASHCARD.getZeroBased());
        UnfavCommand unfavCommand = new UnfavCommand(INDEX_SECOND_FLASHCARD);

        Flashcard unfavouritedFlashcard = new FlashcardBuilder(flashcardToUnfavourite)
                .withFavouriteStatus(false).build();

        String expectedMessage = String.format(UnfavCommand.MESSAGE_UNFAVOURITE_FLASHCARD_SUCCESS,
                unfavouritedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardDeck(model.getFlashcardDeck()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(1), unfavouritedFlashcard);

        assertCommandSuccess(unfavCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_SECOND_FLASHCARD);

        Flashcard flashcardToUnfavourite = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        UnfavCommand unfavCommand = new UnfavCommand(INDEX_FIRST_FLASHCARD);

        Flashcard unfavouritedFlashcard = new FlashcardBuilder(flashcardToUnfavourite)
                .withFavouriteStatus(false).build();

        String expectedMessage = String.format(UnfavCommand.MESSAGE_UNFAVOURITE_FLASHCARD_SUCCESS,
                unfavouritedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardDeck(model.getFlashcardDeck()), new UserPrefs());
        showFlashcardAtIndex(expectedModel, INDEX_SECOND_FLASHCARD);
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), unfavouritedFlashcard);

        assertCommandSuccess(unfavCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        UnfavCommand unfavCommand = new UnfavCommand(outOfBoundIndex);

        assertCommandFailure(unfavCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flashcard list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardDeck().getFlashcardList().size());

        UnfavCommand unfavCommand = new UnfavCommand(outOfBoundIndex);

        assertCommandFailure(unfavCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unfavouriteUnfavouritedFlashcard_throwsCommandException() {
        Flashcard flashcardToUnfavourite = model.getFilteredFlashcardList()
                .get(INDEX_FIRST_FLASHCARD.getZeroBased());

        Flashcard flashcardNotFavourited = new FlashcardBuilder(flashcardToUnfavourite)
                .withFavouriteStatus(false).build();

        UnfavCommand unfavCommand = new UnfavCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = UnfavCommand.MESSAGE_UNFAVOURITE_NOT_FAVOURITED_FLASHCARD_ERROR;

        assertCommandFailure(unfavCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnfavCommand unfavCommand = new UnfavCommand(INDEX_FIRST_FLASHCARD);
        UnfavCommand unfavSecondCommand = new UnfavCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(unfavCommand.equals(unfavCommand));

        // same values -> returns true
        UnfavCommand unfavFirstCommandCopy = new UnfavCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(unfavCommand.equals(unfavFirstCommandCopy));

        // different types -> returns false
        assertFalse(unfavCommand.equals(1));

        // null -> returns false
        assertFalse(unfavCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(unfavCommand.equals(unfavSecondCommand));
    }

}
