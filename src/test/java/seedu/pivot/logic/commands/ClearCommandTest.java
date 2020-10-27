package seedu.pivot.logic.commands;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyPivot_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPivot_success() {
        Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPivot(), new UserPrefs());
        expectedModel.setPivot(new Pivot());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

}
