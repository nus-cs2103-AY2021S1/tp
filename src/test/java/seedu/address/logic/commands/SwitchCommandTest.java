package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class SwitchCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SwitchCommand switchCommand = new SwitchCommand();
        assertThrows(NullPointerException.class, () -> switchCommand.execute(null));
    }

    @Test
    public void execute_afterInit_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.switchModuleList();
        assertCommandSuccess(new SwitchCommand(), model, String.format(
                SwitchCommand.MESSAGE_SWITCH_SUCCESS, expectedModel.getSemester()), expectedModel);
    }

    @Test
    public void execute_afterSwitch_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        model.switchModuleList();
        assertCommandSuccess(new SwitchCommand(), model, String.format(
                SwitchCommand.MESSAGE_SWITCH_SUCCESS, expectedModel.getSemester()), expectedModel);
    }

    @Test
    public void equals() {
        SwitchCommand switchCommand = new SwitchCommand();
        SwitchCommand otherCommand = new SwitchCommand();

        // same object -> returns true
        assertEquals(switchCommand, switchCommand);

        // same type -> returns true
        assertEquals(switchCommand, otherCommand);

        // different types -> returns false
        assertNotEquals(switchCommand, new Object());

        // null -> returns false
        assertNotEquals(switchCommand, null);
    }
}
