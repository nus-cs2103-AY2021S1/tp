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
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;

class ViewCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToView = model.getFilteredFlashcardList()
                .get(INDEX_FIRST_FLASHCARD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FLASHCARD, false);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_FLASHCARD_SUCCESS,
                        flashcardToView), INDEX_FIRST_FLASHCARD.getZeroBased(), false);
        ModelManager expectedModel = new ModelManager(model.getFlashcardDeck(), new UserPrefs());
        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex, false);
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Flashcard flashcardToView = model.getFilteredFlashcardList()
                .get(INDEX_FIRST_FLASHCARD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FLASHCARD, false);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_FLASHCARD_SUCCESS, flashcardToView),
                INDEX_FIRST_FLASHCARD.getZeroBased(), false);
        assertCommandSuccess(viewCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardDeck().getFlashcardList().size());
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex, false);
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_FLASHCARD, false);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_FLASHCARD, false);
        ViewCommand viewThirdCommand = new ViewCommand(INDEX_FIRST_FLASHCARD, true);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_FLASHCARD, false);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));

        // same flashcard but different showAnswer -> returns false
        assertFalse(viewFirstCommand.equals(viewThirdCommand));
    }

}
