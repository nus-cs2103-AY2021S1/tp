package nustorage.logic;

import static nustorage.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nustorage.logic.commands.CommandResult;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.storage.JsonFinanceAccountStorage;
import nustorage.storage.JsonInventoryStorage;
import nustorage.storage.JsonUserPrefsStorage;
import nustorage.storage.StorageManager;


public class LogicManagerTest {

    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));

        JsonFinanceAccountStorage financeAccountStorage =
                new JsonFinanceAccountStorage(temporaryFolder.resolve("financeAccount.json"));
        JsonInventoryStorage inventoryStorage =
                new JsonInventoryStorage(temporaryFolder.resolve("financeAccount.json"));

        StorageManager storage = new StorageManager(financeAccountStorage, inventoryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }


    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void getFilteredInventoryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInventory().remove(0));
    }

    @Test
    public void getFilteredFinanceList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredFinanceList().remove(0));
    }


    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }


    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                       String expectedMessage) {
        Model expectedModel = new ModelManager(new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.setGuiSettings(null));
    }

    @Test
    public void getGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertTrue(logic.getGuiSettings().getWindowHeight() == 600);
        assertTrue(logic.getGuiSettings().getWindowWidth() == 740);

    }
}
