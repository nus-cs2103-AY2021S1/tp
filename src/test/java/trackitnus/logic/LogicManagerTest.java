package trackitnus.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackitnus.testutil.Assert.assertThrows;
import static trackitnus.testutil.typical.TypicalContacts.AMY;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.contact.AddContactCommand;
import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.UserPrefs;
import trackitnus.model.commons.Code;
import trackitnus.model.contact.Contact;
import trackitnus.model.task.Task;
import trackitnus.storage.JsonTrackIterStorage;
import trackitnus.storage.JsonUserPrefsStorage;
import trackitnus.storage.StorageManager;
import trackitnus.testutil.builder.ContactBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private final Model model = new ModelManager();
    @TempDir
    public Path temporaryFolder;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTrackIterStorage trackIterStorage =
            new JsonTrackIterStorage(temporaryFolder.resolve("trackIter.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(trackIterStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = Contact.TYPE + " " + "delete 9";
        assertCommandException(deleteCommand, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTrackIterIoExceptionThrowingStub
        JsonTrackIterStorage trackIterStorage =
            new JsonTrackIterIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTrackIter.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(trackIterStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = Contact.TYPE + " " + AddContactCommand.COMMAND_WORD + ContactCommandTestUtil.NAME_DESC_AMY
            + ContactCommandTestUtil.PHONE_DESC_AMY + ContactCommandTestUtil.EMAIL_DESC_AMY;
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addContact(expectedContact);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredContactList().remove(0));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    @Test
    public void getModuleTasks_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getModuleTasks(new Code("CS2345")).remove(0));
    }

    @Test
    public void getOverdueTasks_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getOverdueTasks().remove(0));
    }

    @Test
    public void getFutureTasks_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFutureTasks().remove(0));
    }

    @Test
    public void getDayUpcomingTasks_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> logic.getDayUpcomingTasks(LocalDate.parse("12/12/2020", Task.FORMATTER)).remove(0));
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
        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
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

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonTrackIterIoExceptionThrowingStub extends JsonTrackIterStorage {
        private JsonTrackIterIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTrackIter(ReadOnlyTrackIter trackIter, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
