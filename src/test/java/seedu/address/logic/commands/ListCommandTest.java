package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private ExerciseModel model;
    private ExerciseModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ExerciseModelManager(getTypicalExerciseBook(), null, new UserPrefs());
        expectedModel = new ExerciseModelManager(model.getExerciseBook(), null, new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
