package seedu.address.logic.commands.exercise;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ExerciseAddCommand}.
 */
public class ExerciseAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitNus(), new UserPrefs());
    }

    @Test
    public void execute_newExercise_success() {
        Exercise validExercise = new ExerciseBuilder().withName("Bench").build();

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.addExercise(validExercise);

        assertCommandSuccess(new ExerciseAddCommand(validExercise), model,
                String.format(ExerciseAddCommand.MESSAGE_SUCCESS, validExercise), expectedModel);
    }

    @Test
    public void execute_duplicateExercise_throwsCommandException() {
        Exercise exerciseInList = model.getFitNus().getExerciseList().get(0);
        assertCommandFailure(new ExerciseAddCommand(exerciseInList), model,
                ExerciseAddCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

}
