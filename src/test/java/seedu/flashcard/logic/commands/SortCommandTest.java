package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.logic.commands.SortCommand.MESSAGE_SORTED_REVIEWED_ASCENDING;
import static seedu.flashcard.logic.commands.SortCommand.MESSAGE_SORTED_REVIEWED_DESCENDING;
import static seedu.flashcard.logic.commands.SortCommand.MESSAGE_SORTED_SUCCESS_ASCENDING;
import static seedu.flashcard.logic.commands.SortCommand.MESSAGE_SORTED_SUCCESS_DESCENDING;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_4;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_5;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_6;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.SortCriteria;
import seedu.flashcard.testutil.TypicalFlashcards;


/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(TypicalFlashcards.getReviewedFlashcardDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalFlashcards.getReviewedFlashcardDeck(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand leastReviewedCommand = new SortCommand(SortCriteria.REVIEWED_ASCENDING);
        SortCommand mostReviewedCommand = new SortCommand(SortCriteria.REVIEWED_DESCENDING);

        // same object -> returns true
        assertTrue(leastReviewedCommand.equals(leastReviewedCommand));

        // same values -> returns true
        SortCommand leastReviewedCommandCopy = new SortCommand(SortCriteria.REVIEWED_ASCENDING);
        assertTrue(leastReviewedCommand.equals(leastReviewedCommandCopy));

        // different types -> returns false
        assertFalse(leastReviewedCommand.equals(1));

        // null -> returns false
        assertFalse(leastReviewedCommand.equals(null));

        // different commands -> returns false
        assertFalse(leastReviewedCommand.equals(mostReviewedCommand));
    }

    @Test
    public void execute_reviewedAscending_success() {
        String expectedMessage = MESSAGE_SORTED_REVIEWED_ASCENDING;
        SortCommand sortCommand = new SortCommand(SortCriteria.REVIEWED_ASCENDING);
        expectedModel.sortFilteredFlashcardList(SortCriteria.REVIEWED_ASCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_5, FLASHCARD_4, FLASHCARD_6), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_reviewedDescending_success() {
        String expectedMessage = MESSAGE_SORTED_REVIEWED_DESCENDING;
        SortCommand sortCommand = new SortCommand(SortCriteria.REVIEWED_DESCENDING);
        expectedModel.sortFilteredFlashcardList(SortCriteria.REVIEWED_DESCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_6, FLASHCARD_4, FLASHCARD_5), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_successAscending_success() {
        String expectedMessage = MESSAGE_SORTED_SUCCESS_ASCENDING;
        SortCommand sortCommand = new SortCommand(SortCriteria.SUCCESS_RATE_ASCENDING);
        expectedModel.sortFilteredFlashcardList(SortCriteria.SUCCESS_RATE_ASCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_5, FLASHCARD_6, FLASHCARD_4), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_successDescending_success() {
        String expectedMessage = MESSAGE_SORTED_SUCCESS_DESCENDING;
        SortCommand sortCommand = new SortCommand(SortCriteria.SUCCESS_RATE_DESCENDING);
        expectedModel.sortFilteredFlashcardList(SortCriteria.SUCCESS_RATE_DESCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_4, FLASHCARD_6, FLASHCARD_5), model.getFilteredFlashcardList());
    }

}
