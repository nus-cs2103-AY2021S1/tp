package seedu.cc.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_DISPLAYED_INDEX;
import static seedu.cc.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.cc.logic.commands.CommandTestUtil.AMOUNT_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.CATEGORY_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.TAG_DESC_ROSES;
import static seedu.cc.model.util.SampleCommonCentsUtilData.initEmptyCommonCents;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalEntries.BUY_ROSE_SEEDS;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.ExitCommand;
import seedu.cc.logic.commands.entrylevel.AddCommand;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.storage.JsonCommonCentsStorage;
import seedu.cc.storage.JsonUserPrefsStorage;
import seedu.cc.storage.StorageManager;
import seedu.cc.testutil.ExpenseBuilder;

public class LogicManagerTest {
    private static final int FIRST_ACCOUNT_INDEX = 0;
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final String DATA_FILE = "CommonCents.json";
    private static final String USERPREFS = "userPrefs.json";
    private static final String INVALID_COMMAND = "uicfhmowqewca";
    private static final String VALID_DELETE_COMMAND = "delete 9 c/revenue";
    private static final String IO_EXCEPTION_FILE = "ioExceptionCommonCents.json";
    private static final String IO_EXCEPTION_USERPREFS = "ioExceptionUserPrefs.json";

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager(initEmptyCommonCents(), new UserPrefs());
    private final ActiveAccount activeAccount =
            new ActiveAccountManager(model.getFilteredAccountList().get(FIRST_ACCOUNT_INDEX));

    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonCommonCentsStorage commonCentsStorage =
                new JsonCommonCentsStorage(temporaryFolder.resolve(DATA_FILE));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve(USERPREFS));
        StorageManager storage = new StorageManager(commonCentsStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = INVALID_COMMAND;
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = VALID_DELETE_COMMAND;
        assertCommandException(deleteCommand, MESSAGE_INVALID_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String exitCommand = ExitCommand.COMMAND_WORD;
        assertCommandSuccess(exitCommand, ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonCommonCentsIoExceptionThrowingStub
        JsonCommonCentsStorage commonCentsStorage =
                new JsonCommonCentsIoExceptionThrowingStub(temporaryFolder.resolve(IO_EXCEPTION_FILE));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve(IO_EXCEPTION_USERPREFS));
        StorageManager storage = new StorageManager(commonCentsStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES;
        Expense expectedExpense = new ExpenseBuilder(BUY_ROSE_SEEDS).build();
        ModelManager expectedModel = new ModelManager(initEmptyCommonCents(), new UserPrefs());
        activeAccount.addExpense(expectedExpense);
        expectedModel.setAccount(activeAccount.getAccount());
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredAccountList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredAccountList().remove(0));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpenseList().remove(0));
    }

    @Test
    public void getFilteredRevenueList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredRevenueList().remove(0));
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
        Model expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());
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
    private static class JsonCommonCentsIoExceptionThrowingStub extends JsonCommonCentsStorage {
        private JsonCommonCentsIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveCommonCents(ReadOnlyCommonCents commonCents, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
