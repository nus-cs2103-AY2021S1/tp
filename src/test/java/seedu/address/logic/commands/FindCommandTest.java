package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.RANDOM1;
import static seedu.address.testutil.TypicalFlashcards.RANDOM2;
import static seedu.address.testutil.TypicalFlashcards.getTypicalQuickCache;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.flashcard.FlashcardContainsTagPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void equals() {
        FlashcardContainsTagPredicate firstPredicate =
                new FlashcardContainsTagPredicate(Collections.singletonList("LSM1301"));
        FlashcardContainsTagPredicate secondPredicate =
                new FlashcardContainsTagPredicate(Collections.singletonList("CS2101"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different keywords -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FlashcardContainsTagPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_oneKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FlashcardContainsTagPredicate predicate = preparePredicate("LSM1301");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RANDOM1, RANDOM2), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code FlashcardContainsTagPredicate}.
     */
    private FlashcardContainsTagPredicate preparePredicate(String userInput) {
        return new FlashcardContainsTagPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
