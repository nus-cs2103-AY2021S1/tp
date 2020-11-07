package com.eva.logic;

import static com.eva.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static com.eva.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static com.eva.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static com.eva.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static com.eva.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static com.eva.testutil.Assert.assertThrows;
//import static com.eva.testutil.TypicalPersons.AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//import com.eva.logic.commands.AddCommand;
import com.eva.logic.commands.CommandResult;
// import com.eva.logic.commands.ListCommand;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.UserPrefs;
import com.eva.model.person.Person;
import com.eva.storage.JsonEvaStorage;
import com.eva.storage.JsonUserPrefsStorage;
import com.eva.storage.StorageManager;
//import com.eva.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonEvaStorage addressBookStorage =
                new JsonEvaStorage(temporaryFolder.resolve("personDatabase.json"),
                        temporaryFolder.resolve("staffDatabase.json"),
                        temporaryFolder.resolve("applicantDatabase.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    /*
    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteStaffCommand = "dels ha";
        assertParseException(deleteStaffCommand,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommandParser.));
    }
     */

    /*
    TODO
    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }
     */

    /*@Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonEvaIoExceptionThrowingStub
        JsonEvaStorage addressBookStorage =
                new JsonEvaIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }*/

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, FileNotFoundException {
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
        Model expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
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
    private static class JsonEvaIoExceptionThrowingStub extends JsonEvaStorage {
        private JsonEvaIoExceptionThrowingStub(Path filePath) {
            super(filePath, filePath, filePath);
        }

        @Override
        public void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
