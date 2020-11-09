package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.GradeBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SetCommand}.
 */
public class SetCommandTest {

    private Model model = new ModelManager(new GradeBook(), new UserPrefs(), new GoalTarget());

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetCommand(null));
    }

    @Test
    public void execute_validGoalTarget_success() {
        GoalTarget goalTarget = new GoalTarget(1);
        SetCommand setCommand = new SetCommand(goalTarget);

        String expectedMessage = String.format(SetCommand.MESSAGE_SUCCESS, goalTarget);

        ModelManager expectedModel = new ModelManager(model.getGradeBook(), new UserPrefs(), new GoalTarget(1));

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listGoalTarget_success() {
        GoalTarget goalTarget = new GoalTarget();
        SetCommand setCommand = new SetCommand(goalTarget);

        String expectedMessage = GoalTarget.GOAL_LIST;

        ModelManager expectedModel = new ModelManager(model.getGradeBook(), new UserPrefs(), new GoalTarget());

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        GoalTarget goalOne = new GoalTarget(1);
        GoalTarget goalTwo = new GoalTarget(2);
        SetCommand setGoalOne = new SetCommand(goalOne);
        SetCommand setGoalTwo = new SetCommand(goalTwo);

        // same object -> returns true
        assertTrue(setGoalOne.equals(setGoalOne));

        // same values -> returns true
        SetCommand setGoalOneCopy = new SetCommand(goalOne);
        assertTrue(setGoalOne.equals(setGoalOneCopy));

        // different types -> returns false
        assertFalse(setGoalOne.equals(1));

        // null -> returns false
        assertFalse(setGoalOne.equals(null));

        // different person -> returns false
        assertFalse(setGoalOne.equals(setGoalTwo));
    }

}
