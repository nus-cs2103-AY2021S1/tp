package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyTaskmaster;
import seedu.address.model.Taskmaster;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.student.NusnetId;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonTaskmasterStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;


public class LoadAttendanceCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @BeforeEach
    public void setUp() {
        JsonTaskmasterStorage taskmasterStorage =
                new JsonTaskmasterStorage(Path.of("data", "storage_test_taskmaster.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storage = new StorageManager(taskmasterStorage, userPrefsStorage);
    }

    @Test
    public void execute_fileDoesNotExist_exceptionThrown() {
        // Remove file if it exists
        String filename = "this_file_should_not_exist";
        Path filepath = Path.of("data", filename + ".json");
        boolean isExistingFile = Files.exists(filepath);
        if (isExistingFile) {
            File file = new File(filepath.toString());
            file.delete();
        }

        Model model = new ModelManager(SampleDataUtil.getSampleTaskmaster(), new UserPrefs());
        StorageCommand loadCommand = new LoadAttendanceCommand(filename);
        loadCommand.initaliseStorage(storage);
        String expectedMessage = LoadAttendanceCommand.MESSAGE_NO_FILE_FOUND;
        assertCommandFailure(loadCommand, model, expectedMessage);
    }

    @Test
    public void execute_loadFileExists_success() {
        String filename = "test_sample_load_file";
        Path filepath = Path.of("data", filename + ".json");
        boolean isExistingFile = Files.exists(filepath);
        if (!isExistingFile) {
            throw new AssertionError("Sample load file for existing file test not found.");
        }

        StorageCommand loadCommand = new LoadAttendanceCommand(filename);
        loadCommand.initaliseStorage(storage);

        try {
            Optional<ReadOnlyTaskmaster> optionalReadOnlyTaskmaster = storage.readTaskmaster();
            Taskmaster taskmaster = new Taskmaster(optionalReadOnlyTaskmaster.orElseThrow());
            Model model = new ModelManager(taskmaster, new UserPrefs());

            List<Attendance> attendances = new ArrayList<>();
            attendances.add(new Attendance(new NusnetId("e0000000"), AttendanceType.PRESENT));
            attendances.add(new Attendance(new NusnetId("e0000001"), AttendanceType.ABSENT));
            attendances.add(new Attendance(new NusnetId("e0000002"), AttendanceType.NO_RECORD));
            taskmaster.updateAttendances(attendances);
            Model expectedModel = new ModelManager(taskmaster, new UserPrefs());

            String successMessage = LoadAttendanceCommand.MESSAGE_LOAD_ATTENDANCE_SUCCESS;
            CommandResult expectedCommandResult = new CommandResult(String.format(successMessage, filename));
            assertCommandSuccess(loadCommand, model, expectedCommandResult, expectedModel);
        } catch (DataConversionException | IOException exception) {
            throw new AssertionError("Failed to read Taskmaster from test storage.");
        }
    }

}
