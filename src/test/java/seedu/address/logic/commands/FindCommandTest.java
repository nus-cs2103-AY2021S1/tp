package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.PULL_UP;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;
import static seedu.address.testutil.TypicalExercise.SIT_UP;
import static seedu.address.testutil.TypicalExercise.SQUAT;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.NameContainsKeywordsPredicateForExercise;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    /*private ExerciseModel model = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());
    private ExerciseModel expectedModel = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicateForExercise firstPredicate =
                new NameContainsKeywordsPredicateForExercise(Collections.singletonList("first"));
        NameContainsKeywordsPredicateForExercise secondPredicate =
                new NameContainsKeywordsPredicateForExercise(Collections.singletonList("second"));

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExerciseFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicateForExercise predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExerciseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExercisesFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicateForExercise predicate = preparePredicate("up sQuat");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PULL_UP, PUSH_UP, SIT_UP, SQUAT), model.getFilteredExerciseList());
    }*/

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    /*private NameContainsKeywordsPredicateForExercise preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicateForExercise(Arrays.asList(userInput.split("\\s+")));
    }*/
}
