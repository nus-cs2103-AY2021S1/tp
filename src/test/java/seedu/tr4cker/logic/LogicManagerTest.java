package seedu.tr4cker.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.EVENT_DATE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.EVENT_NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_DATE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.MANUAL_TASK1;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.commands.ListCommand;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.storage.JsonTr4cker;
import seedu.tr4cker.storage.JsonUserPrefsStorage;
import seedu.tr4cker.storage.StorageManager;
import seedu.tr4cker.testutil.TaskBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTr4cker tr4ckerStorage =
                new JsonTr4cker(temporaryFolder.resolve("tr4cker.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(tr4ckerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTr4ckerIoExceptionThrowingStub
        JsonTr4cker tr4ckerStorage =
                new JsonTr4ckerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTr4cker.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(tr4ckerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_1 + DEADLINE_DESC_1
                + DESCRIPTION_DESC_1;
        Task expectedTask = new TaskBuilder(MANUAL_TASK1).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTask(expectedTask);
        String expectedMessage1 = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage1, expectedModel);

        String addCountdown = CountdownCommand.COMMAND_WORD + EVENT_NAME_DESC_1 + EVENT_DATE_DESC_1;
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_1), new EventDate(VALID_EVENT_DATE_1, false));
        expectedModel.addEvent(expectedEvent);
        String expectedMessage2 = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCountdown, CommandException.class, expectedMessage2, expectedModel);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    @Test
    public void getFilteredPendingTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPendingTaskList().remove(0));
    }

    @Test
    public void getFilteredExpiredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpiredTaskList().remove(0));
    }

    @Test
    public void getFilteredCompletedTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCompletedTaskList().remove(0));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredModuleList().remove(0));
    }

    @Test
    public void getPlannerFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getPlannerFilteredTaskList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEventList().remove(0));
    }

    @Test
    public void getFilteredTodoList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTodoList().remove(0));
    }

    @Test
    public void updateFilteredTodoList_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.updateFilteredTodoList(null));
    }

    @Test
    public void updatePlannerFilteredTaskList_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.updatePlannerFilteredTaskList(null));
    }

    @Test
    public void getTr4cker_success() {
        assertEquals(logic.getTr4cker().getClass(), Tr4cker.class);
    }

    @Test
    public void getGuiSettings_success() {
        assertEquals(logic.getGuiSettings().getClass(), GuiSettings.class);
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
        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
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
    private static class JsonTr4ckerIoExceptionThrowingStub extends JsonTr4cker {
        private JsonTr4ckerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTr4cker(ReadOnlyTr4cker tr4cker, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

}
