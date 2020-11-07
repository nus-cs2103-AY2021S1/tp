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
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for FavCommand.
 */
public class FavCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListFavouriteFlashcard_success() {
        Flashcard flashcardToFavourite = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        FavCommand favCommand = new FavCommand(INDEX_FIRST_FLASHCARD);

        Flashcard favouritedFlashcard = new FlashcardBuilder(flashcardToFavourite).withFavouriteStatus(true).build();

        String expectedMessage = String.format(FavCommand.MESSAGE_FAVOURITE_FLASHCARD_SUCCESS, favouritedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardDeck(model.getFlashcardDeck()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), favouritedFlashcard);

        assertCommandSuccess(favCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Flashcard flashcardToFavourite = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        FavCommand favCommand = new FavCommand(INDEX_FIRST_FLASHCARD);

        Flashcard favouritedFlashcard = new FlashcardBuilder(flashcardToFavourite).withFavouriteStatus(true).build();

        String expectedMessage = String.format(FavCommand.MESSAGE_FAVOURITE_FLASHCARD_SUCCESS, favouritedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardDeck(model.getFlashcardDeck()), new UserPrefs());
        showFlashcardAtIndex(expectedModel, INDEX_FIRST_FLASHCARD);
        expectedModel.setFlashcard(model.getFilteredFlashcardList()
                .get(INDEX_FIRST_FLASHCARD.getZeroBased()), favouritedFlashcard);

        assertCommandSuccess(favCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredListFavouriteFlashcard_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        FavCommand favCommand = new FavCommand(outOfBoundIndex);

        assertCommandFailure(favCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flashcard list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardDeck().getFlashcardList().size());

        FavCommand favCommand = new FavCommand(outOfBoundIndex);

        assertCommandFailure(favCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_favouriteFavouritedFlashcard_throwsCommandException() {
        Flashcard alreadyFavouritedFlashcard = model.getFilteredFlashcardList()
                .get(INDEX_SECOND_FLASHCARD.getZeroBased());
        Flashcard favouritedFlashcard = new FlashcardBuilder(alreadyFavouritedFlashcard)
                .withFavouriteStatus(true).build();

        FavCommand favCommand = new FavCommand(INDEX_SECOND_FLASHCARD);

        String expectedMessage = FavCommand.MESSAGE_FLASHCARD_IS_ALREADY_FAVOURITED;

        assertCommandFailure(favCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        FavCommand favFirstCommand = new FavCommand(INDEX_FIRST_FLASHCARD);
        FavCommand favSecondCommand = new FavCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(favFirstCommand.equals(favFirstCommand));

        // same values -> returns true
        FavCommand favFirstCommandCopy = new FavCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(favFirstCommand.equals(favFirstCommandCopy));

        // different types -> returns false
        assertFalse(favFirstCommand.equals(1));

        // null -> returns false
        assertFalse(favFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(favFirstCommand.equals(favSecondCommand));
    }

}
