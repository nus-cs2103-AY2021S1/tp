package seedu.tasklist.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX;
import static seedu.tasklist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tasklist.logic.commands.CommandTestUtil.DEADLINE_DESC_HW;
import static seedu.tasklist.logic.commands.CommandTestUtil.MODULE_CODE_DESC_HW;
import static seedu.tasklist.logic.commands.CommandTestUtil.NAME_DESC_HW;
import static seedu.tasklist.testutil.Assert.assertThrows;
import static seedu.tasklist.testutil.TypicalAssignments.HW;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.AddCommand;
import seedu.tasklist.logic.commands.CommandResult;
import seedu.tasklist.logic.commands.ListCommand;
import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.logic.parser.exceptions.ParseException;
import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;
import seedu.tasklist.model.ReadOnlyProductiveNus;
import seedu.tasklist.model.UserPrefs;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.storage.JsonProductiveNusStorage;
import seedu.tasklist.storage.JsonUserPrefsStorage;
import seedu.tasklist.storage.StorageManager;
import seedu.tasklist.testutil.AssignmentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonProductiveNusStorage productiveNusStorage =
                new JsonProductiveNusStorage(temporaryFolder.resolve("productivenus.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(productiveNusStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void getFilteredReminderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getRemindedAssignmentList().remove(0));
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()), model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonProductiveNusIoExceptionThrowingStub
        JsonProductiveNusStorage productiveNusStorage =
                new JsonProductiveNusIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionProductiveNus.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(productiveNusStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_HW + DEADLINE_DESC_HW + MODULE_CODE_DESC_HW;
        Assignment expectedAssignment = new AssignmentBuilder(HW).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.preUpdateModel();
        expectedModel.addAssignment(expectedAssignment);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredAssignmentList().remove(0));
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
        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
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
    private static class JsonProductiveNusIoExceptionThrowingStub extends JsonProductiveNusStorage {
        private JsonProductiveNusIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveProductiveNus(ReadOnlyProductiveNus productiveNus, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
