package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.getManagers;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderManager;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.VendorManager;
import seedu.address.storage.JsonPresetManagerStorage;
import seedu.address.storage.JsonProfileManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonVendorManagerStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.TypicalModel;
import seedu.address.testutil.TypicalVendors;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model;
    private Logic logic;

    private VendorManager book;
    private UserPrefs userPrefs;

    @BeforeEach
    public void setUp() {
        this.book = TypicalVendors.getTypicalVendorManager();
        this.userPrefs = new UserPrefs();
        this.model = new ModelManager(book, userPrefs, getManagers(), new OrderManager());

        JsonVendorManagerStorage vendorManagerStorage =
                new JsonVendorManagerStorage(temporaryFolder.resolve("vendorManager.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonPresetManagerStorage orderManagerStorage =
                new JsonPresetManagerStorage(temporaryFolder.resolve("presets.json"));
        JsonProfileManagerStorage profileManagerStorage =
                new JsonProfileManagerStorage(temporaryFolder.resolve("profile.json"));
        StorageManager storage = new StorageManager(
                vendorManagerStorage,
                userPrefsStorage,
                orderManagerStorage,
                profileManagerStorage
        );
        model.selectVendor(0);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_helpCommand_success() {
        String helpCommand = "help";
        try {
            assertCommandSuccess(helpCommand, HelpCommand.SHOWING_HELP_MESSAGE, model);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void getObservableVendorList_success() {
        assertEquals(logic.getObservableVendorList(), getTypicalVendorManager().getVendorList());
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String removeCommand = "remove 9";
        assertCommandException(removeCommand, Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
    }

    //TODO: pass
    //    @Test
    //    public void execute_storageThrowsIoException_throwsCommandException() {
    //        // Setup LogicManager with JsonVendorManagerIoExceptionThrowingStub
    //        JsonVendorManagerStorage vendorManagerStorage =
    //                new JsonVendorManagerIoExceptionThrowingStub(temporaryFolder
    //                .resolve("ioExceptionVendorManager.json"));
    //        JsonUserPrefsStorage userPrefsStorage =
    //                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
    //        StorageManager storage = new StorageManager(vendorManagerStorage, userPrefsStorage);
    //        logic = new LogicManager(model, storage);
    //
    //        // Execute add command
    //        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
    //                + ADDRESS_DESC_AMY;
    //        Vendor expectedVendor = new VendorBuilder(AMY).withTags().build();
    //        ModelManager expectedModel = new ModelManager();
    //        expectedModel.addVendor(expectedVendor);
    //        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
    //        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    //    }

    @Test
    public void getFilteredVendorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getObservableVendorList().remove(0));
    }

    @Test
    public void getFilteredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredMenuItemList().remove(0));
    }

    @Test
    public void getFilteredOrderItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredOrderItemList().remove(0));
    }

    @Test
    public void isSelected_success() {
        model.selectVendor(2);
        assertTrue(logic.isSelected());
    }

    @Test
    public void getVendorManagerFilePath_success() {
        assertEquals(userPrefs.getVendorManagerFilePath(), logic.getVendorManagerFilePath());
    }

    @Test
    public void getGuiSettings_success() {
        assertEquals(model.getGuiSettings(), logic.getGuiSettings());
    }

    @Test
    public void setGuiSettings_success() {
        GuiSettings guiSettings = model.getGuiSettings();
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
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
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
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
    private static class JsonVendorManagerIoExceptionThrowingStub extends JsonVendorManagerStorage {
        private JsonVendorManagerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }
        public void saveVendorManager(ReadOnlyVendorManager vendorManager, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
