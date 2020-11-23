package seedu.stock.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.stock.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.stock.logic.commands.CommandResult;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.storage.JsonSerialNumberSetsBookStorage;
import seedu.stock.storage.JsonStockBookStorage;
import seedu.stock.storage.JsonUserPrefsStorage;
import seedu.stock.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonStockBookStorage stockBookStorage =
                new JsonStockBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonSerialNumberSetsBookStorage serialNumberSetsBookStorage =
                new JsonSerialNumberSetsBookStorage(temporaryFolder.resolve("serialNumber.json"));
        StorageManager storage = new StorageManager(stockBookStorage, userPrefsStorage, serialNumberSetsBookStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsCommandException() throws ParseException, CommandException {
        String invalidCommand = "clea";
        assertCommandFailure(invalidCommand, CommandException.class, MESSAGE_UNKNOWN_COMMAND);
    }


    @Test
    public void execute_validCommand_success() throws Exception {
        String helpCommand = HelpCommand.COMMAND_WORD;
        assertCommandSuccess(helpCommand, HelpCommand.MESSAGE_SUCCESS, model);
    }


    @Test
    public void getFilteredStockList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStockList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException,
                                      SourceCompanyNotFoundException, SerialNumberNotFoundException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getStockBook(), new UserPrefs(), model.getSerialNumberSetsBook());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

}
