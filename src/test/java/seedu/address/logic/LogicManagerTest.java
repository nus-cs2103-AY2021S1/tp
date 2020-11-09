package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.ADD_COMMAND_WORD;
import static seedu.address.commons.core.Messages.CLEAR_COMMAND_WORD;
import static seedu.address.commons.core.Messages.DELETE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FILE_OPS_ERROR_MESSAGE;
import static seedu.address.commons.core.Messages.LIST_COMMAND_WORD;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_CONFIRMATION;
import static seedu.address.commons.core.Messages.MESSAGE_CONFIRMATION_CANCEL;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_CONFIRMATION;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_GRADPAD;
import static seedu.address.commons.core.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_NO_CONFIRMATION;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.YES_COMMAND_WORD;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ModuleBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonGradPadStorage gradPadStorage =
                new JsonGradPadStorage(temporaryFolder.resolve("gradPad.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(gradPadStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete cs2103t";
        assertCommandException(deleteCommand, MESSAGE_EMPTY_GRADPAD);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = LIST_COMMAND_WORD;
        assertCommandSuccess(listCommand, MESSAGE_LIST_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonGradPadIoExceptionThrowingStub
        JsonGradPadStorage gradPadStorage =
                new JsonGradPadIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionGradPad.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(gradPadStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = ADD_COMMAND_WORD + CODE_DESC_CS2103T;
        Module expectedModule = new ModuleBuilder(CS2103T).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addModule(expectedModule);
        String expectedMessage = FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredModuleList().remove(0));
    }

    @Test
    public void getGradPad_validTest() {
        ReadOnlyGradPad actual = logic.getGradPad();
        Model testModel = new ModelManager();
        ReadOnlyGradPad expected = testModel.getGradPad();
        assertEquals(expected, actual);
    }

    @Test
    public void getGradPadFilePath_validTest() {
        Path actual = logic.getGradPadFilePath();
        Model testModel = new ModelManager();
        Path expected = testModel.getGradPadFilePath();
        assertEquals(expected, actual);
    }

    @Test
    public void getGuiSettings_validTest() {
        GuiSettings actual = logic.getGuiSettings();
        Model testModel = new ModelManager();
        GuiSettings expected = testModel.getGuiSettings();
        assertEquals(expected, actual);
    }

    @Test
    public void setGuiSettings_validTest() {
        GuiSettings expected = new GuiSettings();
        logic.setGuiSettings(expected);
        GuiSettings actual = logic.getGuiSettings();
        assertEquals(expected, actual);
    }

    @Test
    public void execute_abortCommand_validTest1() throws CommandException, ParseException {
        model.addModule(CS2103T);
        logic.execute(CLEAR_COMMAND_WORD);
        CommandResult expected = new CommandResult(MESSAGE_CONFIRMATION_CANCEL
                + String.format("\"%s\"", CLEAR_COMMAND_WORD));
        CommandResult actual = logic.execute("n");
        assertEquals(expected, actual);
    }

    @Test
    public void execute_abortCommand_validTest2() throws CommandException, ParseException {
        model.addModule(CS2103T);
        logic.execute(CLEAR_COMMAND_WORD);
        CommandResult expected = new CommandResult(MESSAGE_CONFIRMATION_CANCEL
                + String.format("\"%s\"", CLEAR_COMMAND_WORD));
        CommandResult actual = logic.execute(LIST_COMMAND_WORD);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_confirmCommand_validTest() throws CommandException, ParseException {
        model.addModule(CS2103T);
        CommandResult expected = new CommandResult(MESSAGE_NO_CONFIRMATION);
        logic.execute(CLEAR_COMMAND_WORD);
        logic.execute(YES_COMMAND_WORD);
        CommandResult actual = logic.execute(YES_COMMAND_WORD);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_requiresStallClear_validTest() throws CommandException, ParseException {
        model.addModule(CS2103T);
        CommandResult expected = new CommandResult(MESSAGE_CLEAR_CONFIRMATION);
        CommandResult actual = logic.execute(CLEAR_COMMAND_WORD);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_requiresStallDelete_validTest() throws CommandException, ParseException {
        model.addModule(CS2103T);
        CommandResult expected = new CommandResult(MESSAGE_DELETE_CONFIRMATION + CS2103T);
        CommandResult actual = logic.execute(DELETE_COMMAND_WORD + " " + CS2103T.getModuleCode());
        assertEquals(expected, actual);
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, IOException, DataConversionException {
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
        Model expectedModel = new ModelManager(model.getGradPad(), new UserPrefs());
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
    private static class JsonGradPadIoExceptionThrowingStub extends JsonGradPadStorage {
        private JsonGradPadIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveGradPad(ReadOnlyGradPad gradPad, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
