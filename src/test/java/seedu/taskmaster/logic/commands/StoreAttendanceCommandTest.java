package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.storage.JsonTaskmasterStorage;
import seedu.taskmaster.storage.JsonUserPrefsStorage;
import seedu.taskmaster.storage.Storage;
import seedu.taskmaster.storage.StorageManager;

public class StoreAttendanceCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @BeforeEach
    public void setUp() {
        JsonTaskmasterStorage taskmasterStorage = new JsonTaskmasterStorage(getTempFilePath("tm"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storage = new StorageManager(taskmasterStorage, userPrefsStorage);
    }

    @Test
    public void execute_fileExists_fileOverwritten() {
        // Create file if it does not exist
        String filename = "test_sample_existing_storage_file";
        Path filepath = Path.of("data", filename + ".json");
        boolean isExistingFile = Files.exists(filepath);
        try {
            if (!isExistingFile) {
                File file = new File(filepath.toString());
                file.createNewFile();
            }
        } catch (IOException ioException) {
            throw new AssertionError("Test failed to create new file.", ioException);
        }

        // Run command in the condition where file already exists
        assert(Files.exists(filepath));
        Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        model.changeSession(new SessionName("Typical session"));
        expectedModel.changeSession(new SessionName("Typical session"));
        StorageCommand storeCommand = new StoreAttendanceCommand(filename);
        storeCommand.initialiseStorage(storage);
        String successMessage = StoreAttendanceCommand.MESSAGE_SAVE_SUCCESS_OVERWRITE;
        CommandResult expectedCommandResult = new CommandResult(String.format(successMessage, filename));
        assertCommandSuccess(storeCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_fileDoesNotExist_newFileCreated() {
        // Remove file if it exists
        String filename = "test_sample_non_existing_storage_file";
        Path filepath = Path.of("data", filename + ".json");
        boolean isExistingFile = Files.exists(filepath);
        if (isExistingFile) {
            File file = new File(filepath.toString());
            file.delete();
        }

        // Run command in the condition where file does not exist
        assert(!Files.exists(filepath));
        Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        model.changeSession(new SessionName("Typical session"));
        expectedModel.changeSession(new SessionName("Typical session"));
        StorageCommand storeCommand = new StoreAttendanceCommand(filename);
        storeCommand.initialiseStorage(storage);
        String successMessage = StoreAttendanceCommand.MESSAGE_SAVE_SUCCESS_NEWFILE;
        CommandResult expectedCommandResult = new CommandResult(String.format(successMessage, filename));

        assertCommandSuccess(storeCommand, model, expectedCommandResult, expectedModel);
        boolean isExistingFileAgain = Files.exists(filepath);
        if (isExistingFileAgain) {
            File file = new File(filepath.toString());
            file.delete();
        }
        assert(!Files.exists(filepath));
    }

}
