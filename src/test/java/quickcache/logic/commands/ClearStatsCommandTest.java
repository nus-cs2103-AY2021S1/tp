package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.testutil.TypicalIndexes;

public class ClearStatsCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void equals() {
        ClearStatsCommand clearStatsFirstCommand = new ClearStatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        ClearStatsCommand clearStatsSecondCommand = new ClearStatsCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(clearStatsFirstCommand.equals(clearStatsFirstCommand));

        // same values -> returns true
        ClearStatsCommand clearStatsFirstCommandCopy = new ClearStatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        assertTrue(clearStatsFirstCommand.equals(clearStatsFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearStatsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearStatsFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(clearStatsFirstCommand.equals(clearStatsSecondCommand));
    }

    @Test
    public void execute_validIndex_success() {
        ClearStatsCommand clearStatsCommand = new ClearStatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(ClearStatsCommand.MESSAGE_CLEAR_STATISTICS_FLASHCARD_SUCCESS,
                TypicalIndexes.INDEX_FIRST_FLASHCARD.getOneBased());

        assertCommandSuccess(clearStatsCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of QuickCache list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        ClearStatsCommand clearStatsCommand = new ClearStatsCommand(outOfBoundIndex);

        assertCommandFailure(clearStatsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }
}
