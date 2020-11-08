package seedu.address.logic.commands.exercise;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ExerciseListCommand.
 */
public class ExerciseListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
    }

    @Test
    public void execute_exerciseListIsNotFiltered_showsSameExerciseList() {
        assertCommandSuccess(new ExerciseListCommand(), model, ExerciseListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_exerciseListIsFiltered_showsEverything() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
        assertCommandSuccess(new ExerciseListCommand(), model, ExerciseListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
