package seedu.flashcard.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.logic.commands.AddCommand;
import seedu.flashcard.logic.commands.CommandResult;
import seedu.flashcard.logic.commands.ListCommand;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.storage.JsonFlashcardDeckStorage;
import seedu.flashcard.storage.JsonUserPrefsStorage;
import seedu.flashcard.storage.StorageManager;
import seedu.flashcard.testutil.FlashcardBuilder;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashcard.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.flashcard.logic.commands.CommandTestUtil.*;
import static seedu.flashcard.logic.parser.AddCommandParserTest.*;
import static seedu.flashcard.testutil.Assert.assertThrows;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFlashcardDeckStorage flashcardDeckStorage =
                new JsonFlashcardDeckStorage(temporaryFolder.resolve("flashcardDeck.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(flashcardDeckStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFlashcardDeckIoExceptionThrowingStub
        JsonFlashcardDeckStorage flashcardDeckStorage =
                new JsonFlashcardDeckIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFlashcardDeck.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(flashcardDeckStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + PREFIX_QUESTION + VALID_QUESTION_1 + PREFIX_ANSWER
                + VALID_ANSWER_1 + PREFIX_CATEGORY + VALID_CATEGORY_1 + PREFIX_RATING + VALID_RATING_2
                + PREFIX_TAG + VALID_TAG_2;
        Flashcard expectedFlashcard = new FlashcardBuilder(FLASHCARD_1).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addFlashcard(expectedFlashcard);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredFlashcardList().remove(0));
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
        Model expectedModel = new ModelManager(model.getFlashcardDeck(), new UserPrefs());
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
    private static class JsonFlashcardDeckIoExceptionThrowingStub extends JsonFlashcardDeckStorage {
        private JsonFlashcardDeckIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
