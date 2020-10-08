package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_2;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.CategoryEqualsKeywordsPredicate;
import seedu.address.testutil.TypicalFlashcards;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(TypicalFlashcards.getTypicalFlashcardDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalFlashcards.getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void equals() {
        CategoryEqualsKeywordsPredicate firstPredicate =
                new CategoryEqualsKeywordsPredicate(Collections.singletonList("first"));
        CategoryEqualsKeywordsPredicate secondPredicate =
                new CategoryEqualsKeywordsPredicate(Collections.singletonList("second"));

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_flashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate("c/SDLC");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate("c/SDLC c/Revision History");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1, FLASHCARD_2), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_lowercaseKeyword_success() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate("c/sdlc");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_uppercaseKeyword_success() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate("c/SDLC c/REVISION HISTORY");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1, FLASHCARD_2), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_uppercaseKeyword_reverseOrder_success() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        CategoryEqualsKeywordsPredicate predicate = preparePredicate("c/REVISION HISTORY c/SDLC");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1, FLASHCARD_2), model.getFilteredFlashcardList());
    }
    /**
     * Parses {@code userInput} into a {@code CategoryEqualsKeywordsPredicate}.
     */
    private CategoryEqualsKeywordsPredicate preparePredicate(String userInput) {
        String[] categoryKeywords = userInput.split("c/");
        categoryKeywords = Arrays.copyOfRange(categoryKeywords, 1, categoryKeywords.length);
        return new CategoryEqualsKeywordsPredicate(Arrays.asList(categoryKeywords));
    }
}
