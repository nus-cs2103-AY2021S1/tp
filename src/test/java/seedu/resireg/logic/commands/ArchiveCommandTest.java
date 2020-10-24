package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;

class ArchiveCommandTest {

    @Test
    public void execute_emptyResiReg_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.saveStateResiReg();

        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
