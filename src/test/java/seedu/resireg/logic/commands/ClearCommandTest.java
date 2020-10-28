package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;

public class ClearCommandTest {

    private CommandHistory history = new CommandHistory();

    @Test
    public void execute_emptyResiReg_success() {
        Model model = new ModelManager();
        model.finalizeRooms();
        Model expectedModel = new ModelManager();
        expectedModel.saveStateResiReg();

        assertCommandSuccess(new ClearCommand(), model, history, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyResiReg_success() {
        Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalResiReg(), new UserPrefs());
        expectedModel.setResiReg(new ResiReg());
        expectedModel.saveStateResiReg();

        assertCommandSuccess(new ClearCommand(), model, history, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
