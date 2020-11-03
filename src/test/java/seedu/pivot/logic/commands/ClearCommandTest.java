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
        String expectedMessage = ClearCommand.MESSAGE_CLEAR_SUCCESS;
        Model expectedModel = new ModelManager();
        expectedModel.commitPivot(expectedMessage, true);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonEmptyPivot_success() {
        Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
        String expectedMessage = ClearCommand.MESSAGE_CLEAR_SUCCESS;
        Model expectedModel = new ModelManager(getTypicalPivot(), new UserPrefs());
        expectedModel.setPivot(new Pivot());
        expectedModel.commitPivot(expectedMessage, true);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

}
