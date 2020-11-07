package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATIENT_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.PERIOD_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.TEMP_DESC_AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.ListPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.storage.JsonTaskStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.patient.JsonPatientRecordsStorage;
import seedu.address.storage.rooms.JsonRoomOccupancyStorage;
import seedu.address.testutil.PatientBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonPatientRecordsStorage covigentAppStorage =
                new JsonPatientRecordsStorage(temporaryFolder.resolve("covigentApp.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonRoomOccupancyStorage roomOccupancyStorage =
                new JsonRoomOccupancyStorage(temporaryFolder.resolve("roomsOccupied"));
        JsonTaskStorage taskOccupancyStorage =
                new JsonTaskStorage((temporaryFolder.resolve("task")));
        StorageManager storage =
                new StorageManager(covigentAppStorage, roomOccupancyStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "deletepatient IDoNotExist";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PATIENT_NAME);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListPatientCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListPatientCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonPatientRecordsIoExceptionThrowingStub
        JsonPatientRecordsStorage covigentAppStorage =
                new JsonPatientRecordsIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionCovigentApp.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonRoomOccupancyStorage roomOccupancyStorage =
                new JsonRoomOccupancyStorage(temporaryFolder.resolve("roomsOccupied"));
        StorageManager storage =
                new StorageManager(covigentAppStorage, roomOccupancyStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addPatientCommand = AddPatientCommand.COMMAND_WORD
                + NAME_DESC_AMY + TEMP_DESC_AMY
                + PERIOD_DESC_AMY + PHONE_DESC_AMY + AGE_DESC_AMY;
        Patient expectedPatient = new PatientBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPatient(expectedPatient);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;

        assertCommandFailure(addPatientCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPatientList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonPatientRecordsIoExceptionThrowingStub extends JsonPatientRecordsStorage {
        private JsonPatientRecordsIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePatientRecords(ReadOnlyList<Patient> patientRecords, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
