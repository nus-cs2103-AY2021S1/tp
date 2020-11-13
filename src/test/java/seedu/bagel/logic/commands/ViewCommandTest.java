package seedu.bagel.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.bagel.commons.core.Messages;
import seedu.bagel.commons.core.index.Index;
import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;
import seedu.bagel.model.flashcard.Flashcard;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalBagel(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Flashcard flashcardToView = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_FLASHCARD_SUCCESS, flashcardToView);

        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        expectedModel.viewFlashcard(flashcard -> flashcard.equals(flashcardToView));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of bagel (flashcards) list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBagel().getFlashcardList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
