package seedu.address.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ExerciseFindCommand}.
 */
public class ExerciseFindCommandTest {
    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void equals() {
        ExerciseNameContainsKeywordsPredicate firstPredicate =
                new ExerciseNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ExerciseNameContainsKeywordsPredicate secondPredicate =
                new ExerciseNameContainsKeywordsPredicate(Collections.singletonList("second"));

        ExerciseFindCommand exerciseFindFirstCommand = new ExerciseFindCommand(firstPredicate);
        ExerciseFindCommand exerciseFindSecondCommand = new ExerciseFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(exerciseFindFirstCommand.equals(exerciseFindFirstCommand));

        // same values -> returns true
        ExerciseFindCommand exerciseFindFirstCommandCopy = new ExerciseFindCommand(firstPredicate);
        assertTrue(exerciseFindFirstCommand.equals(exerciseFindFirstCommandCopy));

        // different types -> returns false
        assertFalse(exerciseFindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exerciseFindFirstCommand.equals(null));

        // different exercise -> returns false
        assertFalse(exerciseFindFirstCommand.equals(exerciseFindSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code ExerciseNameContainsKeywordsPredicate}.
     */
    private ExerciseNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ExerciseNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
