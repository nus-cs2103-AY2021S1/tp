package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.biddercommands.ListBidderCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.bidderstorage.JsonBidderAddressBookStorage;
import seedu.address.storage.bidstorage.JsonBidBookStorage;
import seedu.address.storage.meeting.JsonMeetingBookStorage;
import seedu.address.storage.property.JsonPropertyBookStorage;
import seedu.address.storage.sellerstorage.JsonSellerAddressBookStorage;
import seedu.address.testutil.bidder.BidderBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {

        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonBidBookStorage bidBookStorage = new JsonBidBookStorage(temporaryFolder.resolve("bidBook.json"));
        JsonSellerAddressBookStorage sellerAddressBookStorage =
                new JsonSellerAddressBookStorage(temporaryFolder.resolve("selleraddressbook.json"));
        JsonBidderAddressBookStorage bidderAddressBookStorage =
                new JsonBidderAddressBookStorage(temporaryFolder.resolve("bidderaddressbook.json"));
        JsonMeetingBookStorage meetingBookStorage =
                new JsonMeetingBookStorage(temporaryFolder.resolve("meetingaddressbook.json"));
        JsonPropertyBookStorage propertyBookStorage =
                new JsonPropertyBookStorage(temporaryFolder.resolve("propertyBook.json"));
        StorageManager storage = new StorageManager(userPrefsStorage, bidBookStorage,
                bidderAddressBookStorage, sellerAddressBookStorage, meetingBookStorage, propertyBookStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteBidderCommand = "delete-b 9";
        assertCommandException(deleteBidderCommand, MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListBidderCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListBidderCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonBidBookStorage bidBookStorage =
                new JsonBidBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionBidBook.json"));
        JsonBidderAddressBookStorage bidderAddressBookStorage = new
                JsonBidderAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonSellerAddressBookStorage sellerAddressBookStorage = new
                JsonSellerAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonMeetingBookStorage meetingBookStorage = new
                JsonMeetingBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionMeetingBook.json"));
        JsonPropertyBookStorage propertyBookStorage = new
                JsonPropertyBookStorage(temporaryFolder.resolve("ioExceptionPropertyBook.json"));
        StorageManager storage = new StorageManager(userPrefsStorage, bidBookStorage,
                bidderAddressBookStorage, sellerAddressBookStorage, meetingBookStorage, propertyBookStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addBidderCommand = AddBidderCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY;
        Bidder expectedBidder = new BidderBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addBidder(expectedBidder);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addBidderCommand, CommandException.class, expectedMessage, expectedModel);
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
        Model expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
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

    private static class JsonBidBookIoExceptionThrowingStub extends JsonBidBookStorage {
        private JsonBidBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveBidBook(ReadOnlyBidBook bidBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    // ------------------------------ SELLER ---------------------------------- //
    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonSellerAddressBookIoExceptionThrowingStub extends JsonSellerAddressBookStorage {
        private JsonSellerAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    // ------------------------------ BIDDER ---------------------------------- //
    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonBidderAddressBookIoExceptionThrowingStub extends JsonBidderAddressBookStorage {
        private JsonBidderAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveBidderAddressBook(ReadOnlyBidderAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonMeetingBookIoExceptionThrowingStub extends JsonMeetingBookStorage {
        private JsonMeetingBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
