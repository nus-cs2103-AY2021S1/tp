package seedu.pivot.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_TITLE_AMY;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.AMY_BEE_DISAPPEARANCE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.ListCommand;
import seedu.pivot.logic.commands.casecommands.ListCaseCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.storage.JsonPivotStorage;
import seedu.pivot.storage.JsonUserPrefsStorage;
import seedu.pivot.storage.ReferenceStorage;
import seedu.pivot.storage.StorageManager;
import seedu.pivot.storage.testutil.ReferenceStorageStub;
import seedu.pivot.testutil.CaseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;


    @BeforeEach
    public void setUp() throws IOException {


        JsonPivotStorage pivotStorage =
                new JsonPivotStorage(temporaryFolder.resolve("pivot.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        ReferenceStorage referenceStorage = new ReferenceStorageStub(temporaryFolder.resolve("./testDirectory"));
        StorageManager storage = new StorageManager(pivotStorage, userPrefsStorage, referenceStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete case 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        //TODO: check that the state is empty first.
        String listCommand = ListCommand.COMMAND_WORD + " " + TYPE_CASE;
        assertCommandSuccess(listCommand, ListCaseCommand.MESSAGE_LIST_CASE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws IOException {

        final Path referenceTest = temporaryFolder.resolve("./testDirectory");

        // Setup LogicManager with JsonPivotIoExceptionThrowingStub
        JsonPivotStorage pivotStorage =
                new JsonPivotIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionPivot.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        ReferenceStorage referenceStorage = new ReferenceStorageStub(temporaryFolder.resolve("./testDirectory"));
        StorageManager storage = new StorageManager(pivotStorage, userPrefsStorage, referenceStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + " " + AddCommand.TYPE_CASE + PREFIX_WITH_TITLE_AMY;
        Case expectedCase = new CaseBuilder(AMY_BEE_DISAPPEARANCE).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCase(expectedCase);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCaseList().remove(0));
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
        Model expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
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
    private static class JsonPivotIoExceptionThrowingStub extends JsonPivotStorage {
        private JsonPivotIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePivot(ReadOnlyPivot pivot, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
