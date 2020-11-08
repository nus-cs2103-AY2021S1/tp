package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code SetReviewLimitCommand}.
 */
public class SetReviewLimitCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_validLimit_success() {
        long validLimit = 20;
        SetReviewLimitCommand setReviewLimitCommand = new SetReviewLimitCommand(validLimit);

        String expectedMessage = String.format(SetReviewLimitCommand.MESSAGE_SUCCESS, validLimit);

        ModelManager expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
        expectedModel.setReviewCardLimit(validLimit);

        assertCommandSuccess(setReviewLimitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLimit_throwsCommandException() {
        long invalidLimit = 0;
        SetReviewLimitCommand setReviewLimitCommand = new SetReviewLimitCommand(invalidLimit);

        assertCommandFailure(setReviewLimitCommand, model, Messages.MESSAGE_INVALID_LIMIT);
    }

    @Test
    public void equals() {
        SetReviewLimitCommand setReviewLimitCommand10 = new SetReviewLimitCommand(10);
        SetReviewLimitCommand setReviewLimitCommand30 = new SetReviewLimitCommand(30);

        // same object -> returns true
        assertTrue(setReviewLimitCommand10.equals(setReviewLimitCommand10));

        // same values -> returns true
        SetReviewLimitCommand setReviewLimitCommand10Copy = new SetReviewLimitCommand(10);
        assertTrue(setReviewLimitCommand10.equals(setReviewLimitCommand10Copy));

        // different types -> returns false
        assertFalse(setReviewLimitCommand10.equals(10));

        // null -> returns false
        assertFalse(setReviewLimitCommand10.equals(null));

        // different limit -> returns false
        assertFalse(setReviewLimitCommand10.equals(setReviewLimitCommand30));
    }
}

