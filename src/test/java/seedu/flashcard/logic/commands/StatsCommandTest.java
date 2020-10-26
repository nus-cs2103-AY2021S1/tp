package seedu.flashcard.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

class StatsCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToShowStatistics = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(StatsCommand.MESSAGE_VIEW_FLASHCARD_SUCCESS, flashcardToShowStatistics);
        ModelManager expectedModel = new ModelManager(model.getFlashcardDeck(), new UserPrefs());

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        StatsCommand statsCommand = new StatsCommand(outOfBoundIndex);

        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Flashcard flashcardToShowStatistics = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_FLASHCARD);
        String expectedMessage = String.format(StatsCommand.MESSAGE_VIEW_FLASHCARD_SUCCESS, flashcardToShowStatistics);
        assertCommandSuccess(statsCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardDeck().getFlashcardList().size());
        StatsCommand statsCommand = new StatsCommand(outOfBoundIndex);
        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StatsCommand statsFirstCommand = new StatsCommand(INDEX_FIRST_FLASHCARD);
        StatsCommand statsSecondCommand = new StatsCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(statsFirstCommand.equals(statsFirstCommand));

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different types -> returns false
        assertFalse(statsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(statsFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(statsFirstCommand.equals(statsSecondCommand));
    }

}