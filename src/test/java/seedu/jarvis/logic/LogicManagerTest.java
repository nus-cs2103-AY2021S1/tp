package seedu.jarvis.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX;
import static seedu.jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.jarvis.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.add.AddCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.commands.view.ViewAllStudentsCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.UserPrefs;
import seedu.jarvis.model.summary.Summary;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.storage.JsonAddressBookStorage;
import seedu.jarvis.storage.JsonUserLoginStorage;
import seedu.jarvis.storage.JsonUserPrefsStorage;
import seedu.jarvis.storage.StorageManager;
import seedu.jarvis.testutil.TypicalAddressBook;
import seedu.jarvis.testutil.TypicalManagers;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Model typicalModelManager;
    private Logic logic;
    private Logic typicalLogic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("jarvis.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonUserLoginStorage userLoginStorage = new JsonUserLoginStorage(temporaryFolder.resolve("userLogin.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, userLoginStorage);
        logic = new LogicManager(model, storage);

        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        typicalLogic = new LogicManager(typicalModelManager, storage);
    }

    @Test
    public void getAddressBook_returnCorrectAddressBook_success() {
        assertEquals(new AddressBook(), logic.getAddressBook());
    }

    @Test
    public void getAddressBookPath_returnAddressBookPath_success() {
        assertEquals(new UserPrefs().getAddressBookFilePath(), logic.getAddressBookFilePath());
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete -mc 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String viewAllStudentsCommand = ViewAllStudentsCommand.SAMPLE_COMMAND;
        assertCommandSuccess(viewAllStudentsCommand, ViewAllStudentsCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonUserLoginStorage userLoginStorage = new JsonUserLoginStorage(
                temporaryFolder.resolve("ioExceptionUserLogin.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, userLoginStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + " -t workout";
        Todo todo = new Todo("workout");
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTodo(todo);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
    }

    @Test
    public void updateAllSummaryDetails_summaryDetailsAccurate() {

        String summaryDetails = typicalLogic.getSummary().get();
        int expectedMissions = 3;
        int expectedQuests = 3;
        int expectedConsultations = 3;
        int expectedMasteryChecks = 3;
        int expectedTasks = 6;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_REMAINING).append(" - ");
        stringBuilder.append(Summary.MESSAGE_MISSIONS).append(": ").append(expectedMissions)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_QUESTS).append(": ").append(expectedQuests)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(expectedConsultations)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(expectedMasteryChecks)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_TASKS).append(": ").append(expectedTasks);
        String stringExpected = stringBuilder.toString();

        assertEquals(stringExpected, summaryDetails);
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
        Model expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
