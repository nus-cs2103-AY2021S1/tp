package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBudgets;

class UndoCommandTest {
    private final Model model = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());

    @Test
    public void execute_deleteAllBudgets_undidCorrectly() {
        model.saveToHistory();
        model.deleteAllBudgets();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noActionsToUndo_undidCorrectly() {
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE, expectedModel);
    }
}
