package seedu.address.logic.commands.play;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_DECK;
import static seedu.address.commons.core.Messages.MESSAGE_NO_DECK_SELECTED;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.LogicTestHelper;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonWordBankStorage;
import seedu.address.storage.StorageManager;

public class PlayCommandTest {

    @TempDir
    public Path temporaryFolder;
    private Model model = new ModelManager();
    private Entry entry = new Entry(new Word("abc"), new Translation("123"));
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
        logicTestHelper = new LogicTestHelper(this.logic, this.model);
    }

    @Test
    public void execute_singlePlayCommandWithoutSelectedDeck_throwCommandException() {
        logicTestHelper.assertCommandException("/play", MESSAGE_NO_DECK_SELECTED);
    }

    @Test
    public void execute_singlePlayCommandSelectedButEmptyDeck_throwCommandException() {
        model.addDeck(deck);
        model.selectDeck(Index.fromZeroBased(0));
        logicTestHelper.assertCommandException("/play", MESSAGE_EMPTY_DECK);
    }

    @Test
    public void execute_singlePlayCommandWithSelectedAndFullDeck_success() throws Exception {
        deck.addEntry(entry);
        model.addDeck(deck);
        model.selectDeck(Index.fromZeroBased(0));
        logicTestHelper.assertCommandSuccess("/play", "Playmode Started", model);
    }

    @Test
    public void execute_doublePlayCommandWithSelectedDeck_throwCommandException() throws CommandException,
            ParseException {
        deck.addEntry(entry);
        model.addDeck(deck);
        model.selectDeck(Index.fromZeroBased(0));
        logicTestHelper.assertCommandSuccess("/play", "Playmode Started", model);
        logicTestHelper.assertCommandException("/play", "Already in play mode");
    }

}
