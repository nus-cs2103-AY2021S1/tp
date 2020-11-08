package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Weight;
import seedu.address.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private ExerciseModel model;

    @BeforeEach
    public void setUp() {
        model = new ExerciseModelManager(getTypicalExerciseBook(), null, new UserPrefs());
    }

    @Test
    public void execute_newExercise_success() {
        try {
            Exercise validExercise = new ExerciseBuilder().build();

            ExerciseModel expectedModel =
                    new ExerciseModelManager(model.getExerciseBook(), null, new UserPrefs());
            expectedModel.addExercise(validExercise);

            String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, validExercise)
                    + String.format(AddCommand.MESSAGE_WEIGHT, new Weight(validExercise.getCalories()).toString());

            assertCommandSuccess(new AddCommand(validExercise), model, expectedMessage, expectedModel);

        } catch (CaloriesOverflow err) {
            throw new AssertionError(err);
        } catch (Exception err) {
            throw new AssertionError(err);
        }
    }

}
