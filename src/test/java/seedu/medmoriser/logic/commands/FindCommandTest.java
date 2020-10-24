package seedu.medmoriser.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medmoriser.commons.core.Messages.MESSAGE_QANDA_LISTED_OVERVIEW;
import static seedu.medmoriser.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medmoriser.testutil.TypicalQAndA.CARL;
import static seedu.medmoriser.testutil.TypicalQAndA.ELLE;
import static seedu.medmoriser.testutil.TypicalQAndA.FIONA;
import static seedu.medmoriser.testutil.TypicalQAndA.getTypicalMedmoriser;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.medmoriser.model.Model;
import seedu.medmoriser.model.ModelManager;
import seedu.medmoriser.model.UserPrefs;
import seedu.medmoriser.model.qanda.AnswerContainsKeywordsPredicate;
import seedu.medmoriser.model.qanda.QuestionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalMedmoriser(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMedmoriser(), new UserPrefs());

    @Test
    public void equals() {
        QuestionContainsKeywordsPredicate firstPredicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("first"));
        QuestionContainsKeywordsPredicate secondPredicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("second"));

        AnswerContainsKeywordsPredicate thirdPredicate =
                new AnswerContainsKeywordsPredicate(Collections.singletonList("first"));
        AnswerContainsKeywordsPredicate fourthPredicate =
                new AnswerContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        FindCommand findThirdCommand = new FindCommand(thirdPredicate);
        FindCommand findFourthCommand = new FindCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findThirdCommand.equals(findThirdCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        FindCommand findThirdCommandCopy = new FindCommand(thirdPredicate);
        assertTrue(findThirdCommand.equals(findThirdCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different questionSet -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findThirdCommand.equals(findFourthCommand));
    }

    @Test
    public void execute_zeroKeywords_noQuestionSetFound() {
        String expectedMessage = String.format(MESSAGE_QANDA_LISTED_OVERVIEW, 0);
        QuestionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredQAndAList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredQAndAList());
    }

    @Test
    public void execute_multipleKeywords_multipleQuestionSetsFound() {
        String expectedMessage = String.format(MESSAGE_QANDA_LISTED_OVERVIEW, 3);
        QuestionContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredQAndAList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredQAndAList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private QuestionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new QuestionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
