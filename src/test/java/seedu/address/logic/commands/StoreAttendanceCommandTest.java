package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalTaskmaster;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonTaskmasterStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class StoreAttendanceCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @BeforeEach
    public void setUp() {
        JsonTaskmasterStorage taskmasterStorage = new JsonTaskmasterStorage(getTempFilePath("ab"));
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
        Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        StorageCommand storeCommand = new StoreAttendanceCommand(filename);
        storeCommand.initaliseStorage(storage);
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
        Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        StorageCommand storeCommand = new StoreAttendanceCommand(filename);
        storeCommand.initaliseStorage(storage);
        String successMessage = StoreAttendanceCommand.MESSAGE_SAVE_SUCCESS_NEWFILE;
        CommandResult expectedCommandResult = new CommandResult(String.format(successMessage, filename));
        assertCommandSuccess(storeCommand, model, expectedCommandResult, expectedModel);
    }

}
