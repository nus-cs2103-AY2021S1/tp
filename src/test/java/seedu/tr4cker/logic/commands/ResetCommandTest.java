package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.UserPrefs;

public class ResetCommandTest {

    @Test
    public void execute_emptyTr4cker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ResetCommand(), model, ResetCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTr4cker_success() {
        Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTr4cker(), new UserPrefs());
        expectedModel.setTr4cker(new Tr4cker());

        assertCommandSuccess(new ResetCommand(), model, ResetCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
