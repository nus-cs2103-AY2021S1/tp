package seedu.expense.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX;
import static seedu.expense.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DATE_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DESCRIPTION_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.FOOD;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.expense.logic.commands.AddCommand;
import seedu.expense.logic.commands.CommandResult;
import seedu.expense.logic.commands.ListCommand;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.Expense;
import seedu.expense.storage.JsonAliasMapStorage;
import seedu.expense.storage.JsonExpenseBookStorage;
import seedu.expense.storage.JsonUserPrefsStorage;
import seedu.expense.storage.StorageManager;
import seedu.expense.testutil.ExpenseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonExpenseBookStorage expenseBookStorage =
                new JsonExpenseBookStorage(temporaryFolder.resolve("expenseBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonAliasMapStorage aliasMapStorage = new JsonAliasMapStorage(temporaryFolder.resolve("aliasMap.json"));
        StorageManager storage = new StorageManager(expenseBookStorage, userPrefsStorage, aliasMapStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonExpenseBookIoExceptionThrowingStub
        JsonExpenseBookStorage expenseBookStorage =
                new JsonExpenseBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionExpenseBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonAliasMapStorage aliasMapStorage =
                new JsonAliasMapStorage(temporaryFolder.resolve("ioExceptionAliasMap.json"));
        StorageManager storage = new StorageManager(expenseBookStorage, userPrefsStorage, aliasMapStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + DESCRIPTION_DESC_FOOD + AMOUNT_DESC_FOOD
                + DATE_DESC_FOOD + TAG_DESC_FOOD;
        Expense expectedExpense = new ExpenseBuilder(FOOD).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCategory(expectedExpense.getTag());
        expectedModel.addExpense(expectedExpense);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpenseList().remove(0));
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
        Model expectedModel = new ModelManager(model.getExpenseBook(), new UserPrefs(), new AliasMap());
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
    private static class JsonExpenseBookIoExceptionThrowingStub extends JsonExpenseBookStorage {
        private JsonExpenseBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExpenseBook(ReadOnlyExpenseBook expenseBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
