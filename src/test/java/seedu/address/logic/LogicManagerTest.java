package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.budget.DeleteExpenditureCommand.MESSAGE_DELETE_EXPENDITURE_SUCCESS;
import static seedu.address.logic.commands.main.DeleteBudgetCommand.MESSAGE_DELETE_BUDGET_SUCCESS;
import static seedu.address.model.budget.Threshold.NO_THRESHOLD_MESSAGE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.budget.ListExpenditureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.main.ListBudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.UserPrefs;
import seedu.address.model.budget.Threshold;
import seedu.address.state.Page;
import seedu.address.state.PageTitle;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.storage.JsonNusaveStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveStorage(temporaryFolder.resolve("nusave.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);
        model = new ModelManager(getTypicalNusave(), new UserPrefs());
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() throws ParseException, CommandException {
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveStorage(temporaryFolder.resolve("nusave.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);

        logic = new LogicManager(null, storage);
        assertThrows(NullPointerException.class, () -> logic.execute("delete 1"));
    }

    @Test
    public void execute_unknownPage_throwsCommandException() throws ParseException, CommandException {
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveStorage(temporaryFolder.resolve("nusave.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);

        model = new ModelManager(getTypicalNusave(), new UserPrefs());
        model.setPage(Page.TEST);

        logic = new LogicManager(model, storage);
        assertThrows(CommandException.class, () -> logic.execute("delete 1"));
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() throws ParseException, CommandException {
        String invalidCommand = "uicfhmowqewca";
        assertThrows(ParseException.class, () -> logic.execute(invalidCommand));
    }

    @Test
    public void execute_validDeleteCommandMainPage_success() throws CommandException, ParseException {
        String deleteCommand = "delete 2";
        assertCommandSuccess(deleteCommand, MESSAGE_DELETE_BUDGET_SUCCESS, model);
    }

    @Test
    public void execute_validListCommandMainPage_success() throws CommandException, ParseException {
        String listCommand = ListBudgetCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListBudgetCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_validDeleteCommandBudgetPage_success() throws CommandException, ParseException {
        String deleteCommand = "delete 2";
        model.openBudget(new BudgetIndexManager(0));
        assertCommandSuccess(deleteCommand, MESSAGE_DELETE_EXPENDITURE_SUCCESS, model);
    }

    @Test
    public void execute_validListCommandBudgetPage_success() throws Exception {
        String listCommand = ListBudgetCommand.COMMAND_WORD;
        model.openBudget(new BudgetIndexManager(0));
        assertCommandSuccess(listCommand, ListExpenditureCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute create command
        String createCommand = "create n/McDonalds p/100";
        ModelManager expectedModel = new ModelManager(getTypicalNusave(), new UserPrefs());
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertThrows(CommandException.class, expectedMessage, () -> logic.execute(createCommand));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredRenderableList().remove(0));
    }

    @Test
    void getNusaveFilePath() {
        Path actual = Paths.get("data", "nusave.json");
        assertEquals(actual, logic.getNusaveFilePath());
    }

    @Test
    void getGuiSettings() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(guiSettings, logic.getGuiSettings());
    }

    @Test
    void getIsBudgetPageProp() {
        BooleanProperty isBudgetPageProp = new SimpleBooleanProperty(false);
        BooleanProperty actual = logic.getIsBudgetPageProp();
        assertEquals(isBudgetPageProp.getValue(), actual.getValue());
        assertEquals(isBudgetPageProp.getClass(), actual.getClass());
    }

    @Test
    void getThresholdStringProp() {
        StringProperty thresholdStringProp = new SimpleStringProperty(NO_THRESHOLD_MESSAGE);
        StringProperty actual = logic.getThresholdStringProp();
        assertEquals(thresholdStringProp.getValue(), actual.getValue());
        assertEquals(thresholdStringProp.getClass(), actual.getClass());
    }

    @Test
    void getPageTitle() {
        String pageTitleActual = PageTitle.MAIN_PAGE_TITLE;
        assertEquals(pageTitleActual, logic.getPageTitle());
    }

    @Test
    void getTotalExpenditureValue_isMainPage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> logic.getTotalExpenditureValue());
    }

    @Test
    void getTotalExpenditureValue_isBudgetPage_success() throws ParseException, CommandException {
        logic.execute("open 1");
        assertEquals("12.00", logic.getTotalExpenditureValue());
    }

    @Test
    void getThreshold_isMainPage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> logic.getThreshold());
    }

    @Test
    void getThreshold_isBudgetPage_throwsAssertionError() throws ParseException, CommandException {
        logic.execute("open 1");
        assertEquals(new Threshold("80").toOptional(), logic.getThreshold());
    }

    @Test
    void setGuiSettings() {
        double windowWidth = 200;
        double windowHeight = 200;
        int xPosition = 100;
        int yPosition = 100;
        logic.setGuiSettings(new GuiSettings(windowWidth, windowHeight, xPosition, yPosition));
        GuiSettings actual = logic.getGuiSettings();
        assertEquals(windowWidth, actual.getWindowWidth());
        assertEquals(windowHeight, actual.getWindowHeight());
        assertEquals(xPosition, actual.getWindowCoordinates().x);
        assertEquals(yPosition, actual.getWindowCoordinates().y);
    }

    @Test
    void isBudgetPage_isMainPage_false() {
        assertFalse(logic.isBudgetPage());
    }

    @Test
    void isBudgetPage_isBudgetPage_true() throws ParseException, CommandException {
        logic.execute("open 1");
        assertTrue(logic.isBudgetPage());
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
        Model expectedModel = new ModelManager(model.getNusave(), new UserPrefs());
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
    private static class JsonNusaveIoExceptionThrowingStub extends JsonNusaveStorage {
        private JsonNusaveIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveNusave(ReadOnlyNusave addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
