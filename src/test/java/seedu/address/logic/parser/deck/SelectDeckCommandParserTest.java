package seedu.address.logic.parser.deck;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deck.SelectDeckCommand;

public class SelectDeckCommandParserTest {

    private SelectDeckCommandParser parser = new SelectDeckCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectDeckCommand.MESSAGE_USAGE));
    }
}
