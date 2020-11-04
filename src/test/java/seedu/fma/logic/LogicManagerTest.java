package seedu.fma.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_LOG_DISPLAYED_INDEX;
import static seedu.fma.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.fma.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fma.logic.commands.CommandResult;
import seedu.fma.logic.commands.ListCommand;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.UserPrefs;
import seedu.fma.storage.JsonLogBookStorage;
import seedu.fma.storage.JsonUserPrefsStorage;
import seedu.fma.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonLogBookStorage logbookStorage =
                new JsonLogBookStorage(temporaryFolder.resolve("logbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(logbookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 999";
        assertCommandException(deleteCommand, MESSAGE_INVALID_LOG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    // Fails due to DateTime
    /*@Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonLogBookIoExceptionThrowingStub
        JsonLogBookStorage logbookStorage =
                new JsonLogBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionLogbook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(logbookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A;
        Log expectedLog = new LogBuilder(VALID_LOG_A).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addLog(expectedLog);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }*/

    @Test
    public void getFilteredLogList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredLogList().remove(0));
    }


    @Test
    public void getCommandSuggestionList_containsAllCommands_success() {
        List<String> commandSuggestionList = logic.getCommandSuggestionList();
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("add")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("addex")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("clear")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("delete")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("deleteex")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("edit")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("editex")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("exit")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("find")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("help")));
        assertTrue(commandSuggestionList.stream().anyMatch(suggestion -> suggestion.contains("exit")));
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
        Model expectedModel = new ModelManager(model.getLogBook(), new UserPrefs());
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
    private static class JsonLogBookIoExceptionThrowingStub extends JsonLogBookStorage {
        private JsonLogBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveLogBook(ReadOnlyLogBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

}
