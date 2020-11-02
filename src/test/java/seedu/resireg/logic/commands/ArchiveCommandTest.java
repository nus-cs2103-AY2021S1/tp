package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.testutil.StorageStub;

class ArchiveCommandTest {

    private CommandHistory history = new CommandHistory();

    @Test
    public void execute_archivalSuccessResiReg_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.saveStateResiReg();

        PassingStorageStub storageStub = new PassingStorageStub();
        try {
            CommandResult result = new ArchiveCommand().execute(model, storageStub, history);
            assertEquals(new CommandResult(ArchiveCommand.MESSAGE_SUCCESS), result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void execute_archivalFailureResiReg_failure() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.saveStateResiReg();

        FailingStorageStub storageStub = new FailingStorageStub();
        assertThrows(CommandException.class, () -> new ArchiveCommand().execute(model, storageStub, history));
    }

    private static class PassingStorageStub extends StorageStub {
        @Override
        public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
        }
    }

    private static class FailingStorageStub extends StorageStub {
        @Override
        public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
            throw new IOException("This should fail");
        }
    }
}
