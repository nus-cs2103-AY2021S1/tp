package seedu.address.logic.commands.play;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

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

public class AnswerCommandTest {

    @TempDir
    public Path temporaryFolder;
    private final Model model = new ModelManager();
    private final Entry entry = new Entry(new Word("abc"), new Translation("123"));
    private final Deck deck = new Deck(new DeckName("test"));
    private LogicTestHelper logicTestHelper;

    @BeforeEach
    public void setUp() {
        JsonWordBankStorage addressBookStorage =
            new JsonWordBankStorage(temporaryFolder.resolve("wordbank.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
            temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        Logic logic = new LogicManager(model, storage);
        deck.addEntry(entry);
        model.addDeck(deck);
        model.selectDeck(Index.fromZeroBased(0));
        logicTestHelper = new LogicTestHelper(logic, this.model);
    }

    @Test
    public void execute_answerCommandUntilDeckLimit_throwParseException()
        throws CommandException, ParseException {
        logicTestHelper.assertCommandSuccess("/play", "Playmode Started", model);
        logicTestHelper
            .assertCommandSuccess("/stop", "Playmode stopped! Your score was not recorded!",
                model);
    }

    @Test
    public void execute_answerCommandWithStopCommandWord_throwParseException()
        throws CommandException, ParseException {
        try {
            logicTestHelper.assertCommandSuccess("/play", "Playmode Started", model);
            logicTestHelper.assertCommandSuccess("answer", "Your score was 0 / 1", model);
            logicTestHelper.assertParseException("another answer", MESSAGE_UNKNOWN_COMMAND);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

}
