package seedu.address.logic;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_DECK;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonWordBankStorage;
import seedu.address.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;
    private Model model = new ModelManager();
    private Deck deck = new Deck(new DeckName("test"));
    private Logic logic;
    private LogicTestHelper logicTestHelper;

    @BeforeEach
    public void setUp() {
        JsonWordBankStorage addressBookStorage =
                new JsonWordBankStorage(temporaryFolder.resolve("wordbank.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        model.addDeck(deck);
        model.selectDeck(Index.fromZeroBased(0));
        logicTestHelper = new LogicTestHelper(this.logic, this.model);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        logicTestHelper.assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        logicTestHelper.assertCommandException(deleteCommand, MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void getFilteredDeckList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredDeckList().remove(0));
    }

    @Test
    public void execute_singlePlayCommandEmptyDeck_throwsCommandException() {
        logicTestHelper.assertCommandException("/play", MESSAGE_EMPTY_DECK);
    }

}
