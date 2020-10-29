package seedu.resireg.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.resireg.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.resireg.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.CommandResult;
import seedu.resireg.logic.commands.DeleteCommand;
import seedu.resireg.logic.commands.HistoryCommand;
import seedu.resireg.logic.commands.ListCommand;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.JsonResiRegStorage;
import seedu.resireg.storage.JsonUserPrefsStorage;
import seedu.resireg.storage.StorageManager;
import seedu.resireg.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        JsonResiRegStorage resiRegStorage =
                new JsonResiRegStorage(temporaryFolder.resolve("resireg.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(resiRegStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
        assertHistoryCorrect(invalidCommand);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = DeleteCommand.COMMAND_WORD + " 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertHistoryCorrect(deleteCommand);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
        assertHistoryCorrect(listCommand);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonResiRegIoExceptionThrowingStub
        JsonResiRegStorage resiRegStorage =
                new JsonResiRegIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionResiReg.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(resiRegStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + FACULTY_DESC_AMY + STUDENT_ID_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        expectedModel.saveStateResiReg();
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
        assertHistoryCorrect(addCommand);
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
        Model expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
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
     * Asserts that the result display shows all the {@expectedCommands}
     * upon the execution of {@code HistoryCommand}
     */
    private void assertHistoryCorrect(String ...expectedCommands) {
        try {
            CommandResult result = logic.execute(HistoryCommand.COMMAND_WORD);
            String expectedMessage = String.format(
                    HistoryCommand.MESSAGE_SUCCESS, String.join("\n",
                            expectedCommands));
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            throw new AssertionError("Parsing and execution of HistoryCommand.COMMAND_WORD should succeed.", e);
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonResiRegIoExceptionThrowingStub extends JsonResiRegStorage {
        private JsonResiRegIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
