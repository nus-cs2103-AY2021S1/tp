package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.storage.Storage;

class ArchiveCommandTest {

    @Test
    public void execute_archivalSuccessResiReg_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.saveStateResiReg();

        PassingStorageStub storageStub = new PassingStorageStub();
        try {
            CommandResult result = new ArchiveCommand().execute(model, storageStub);
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
        assertThrows(CommandException.class, () -> new ArchiveCommand().execute(model, storageStub));
    }

    /**
     * A stub class for Storage.
     */
    private static class StorageStub implements Storage {
        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getResiRegFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {


            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
            throw new AssertionError("This method should not be called.");
        }
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
