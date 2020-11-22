package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTRACT_EXPIRY_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COUNTRY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.logic.commands.ClientListCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyTbmManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.storage.JsonTbmManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ClientBuilder;

public class LogicManagerTest {

    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private StorageManager storage;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTbmManagerStorage tbmManagerStorage =
                new JsonTbmManagerStorage(temporaryFolder.resolve("tbmManager.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        storage = new StorageManager(tbmManagerStorage, userPrefsStorage);
        model.setWidgetClient(AMY);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LogicManager(model, null));
        assertThrows(NullPointerException.class, () -> new LogicManager(null, storage));
    }

    @Test
    public void getMethods_returnCorrectObjects() {
        assertEquals(logic.getTbmManager(), model.getTbmManager());
        assertEquals(logic.getTbmManagerFilePath(), model.getTbmManagerFilePath());
        assertEquals(logic.getFilteredClientList(), model.getSortedFilteredClientList());
        assertEquals(logic.getWidgetClient(), model.getWidgetClient());
        assertEquals(logic.getGuiSettings(), model.getGuiSettings());
    }

    @Test
    public void setGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(15.0, 10.0, 90, 100);
        assertNotEquals(logic.getGuiSettings(), guiSettings);
        logic.setGuiSettings(guiSettings);
        assertEquals(logic.getGuiSettings(), guiSettings);
    }

    @Test
    public void setCountryNotesListPanelIsVisible_propagatesToModel() {
        // default value
        assertFalse(model.getCountryNotesListPanelIsVisible());

        logic.setCountryNotesListPanelIsVisible(true);
        assertTrue(model.getCountryNotesListPanelIsVisible());
        logic.setCountryNotesListPanelIsVisible(false);
        assertFalse(model.getCountryNotesListPanelIsVisible());
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "client delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ClientListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ClientListCommand.MESSAGE_SUCCESS_NO_CLIENTS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTbmManagerIoExceptionThrowingStub
        JsonTbmManagerStorage tbmManagerStorage =
                new JsonTbmManagerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTbmManager.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(tbmManagerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = ClientAddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + COUNTRY_DESC_AMY + TIMEZONE_DESC_AMY + CONTRACT_EXPIRY_DATE_DESC_AMY;
        Client expectedClient = new ClientBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addClient(expectedClient);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredClientList().remove(0));
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
        Model expectedModel = new ModelManager(model.getTbmManager(), new UserPrefs());
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
    private static class JsonTbmManagerIoExceptionThrowingStub extends JsonTbmManagerStorage {
        private JsonTbmManagerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTbmManager(ReadOnlyTbmManager tbmManager, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

}
