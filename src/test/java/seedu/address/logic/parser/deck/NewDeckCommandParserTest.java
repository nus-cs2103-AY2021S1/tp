package seedu.address.logic.parser.deck;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_DECK_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_NAME_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_NAME_SPANISH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.deck.TypicalDecks.JAPANESE_DECK;
import static seedu.address.testutil.deck.TypicalDecks.SPANISH_DECK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deck.NewDeckCommand;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.testutil.deck.DeckBuilder;

public class NewDeckCommandParserTest {

    private NewDeckCommandParser parser = new NewDeckCommandParser();

    @Test
    public void parse_fieldsPresent_success() {
        Deck expectedDeck = new DeckBuilder(SPANISH_DECK).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_DECK_NAME_SPANISH,
                new NewDeckCommand(expectedDeck));
        expectedDeck = new DeckBuilder(JAPANESE_DECK).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_DECK_NAME_JAPANESE,
                new NewDeckCommand(expectedDeck));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid word
        assertParseFailure(parser, INVALID_DECK_NAME, String.format(DeckName.MESSAGE_CONSTRAINTS,
                NewDeckCommand.MESSAGE_USAGE));
    }
}
