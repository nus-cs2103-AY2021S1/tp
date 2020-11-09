package seedu.taskmaster.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.taskmaster.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NUSNETID_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.taskmaster.testutil.Assert.assertThrows;
import static seedu.taskmaster.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taskmaster.logic.commands.AddCommand;
import seedu.taskmaster.logic.commands.CommandResult;
import seedu.taskmaster.logic.commands.ListStudentsCommand;
import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.logic.parser.exceptions.ParseException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.storage.JsonTaskmasterStorage;
import seedu.taskmaster.storage.JsonUserPrefsStorage;
import seedu.taskmaster.storage.StorageManager;
import seedu.taskmaster.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTaskmasterStorage taskmasterStorage =
                new JsonTaskmasterStorage(temporaryFolder.resolve("taskmaster.json"),
                        temporaryFolder.resolve("session_list.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(taskmasterStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete-student 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListStudentsCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListStudentsCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTaskmasterIoExceptionThrowingStub
        JsonTaskmasterStorage taskmasterStorage =
                new JsonTaskmasterIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTaskmaster.json"),
                        temporaryFolder.resolve("ioExceptionSessionList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(taskmasterStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY
                + NUSNETID_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
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
        Model expectedModel = new ModelManager(model.getTaskmaster(), new UserPrefs());
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
    private static class JsonTaskmasterIoExceptionThrowingStub extends JsonTaskmasterStorage {
        private JsonTaskmasterIoExceptionThrowingStub(Path taskmasterFilePath, Path sessionListFilePath) {
            super(taskmasterFilePath, sessionListFilePath);
        }

        @Override
        public void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
