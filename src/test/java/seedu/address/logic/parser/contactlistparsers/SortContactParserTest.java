package seedu.address.logic.parser.contactlistparsers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contactlistcommands.SortContactCommand;

public class SortContactParserTest {

    private SortContactParser parser = new SortContactParser();

    @Test
    public void parse_validArgs_returnsSortContactCommand() throws Exception {

        // empty string
        assertDoesNotThrow(() -> parser.parse(""));

        // with trailing whitespaces
        assertDoesNotThrow(() -> parser.parse(" "));
        assertDoesNotThrow(() -> parser.parse("   "));

        // valid reversed [r] indicator
        assertDoesNotThrow(() -> parser.parse("r"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // invalid input that has more than one word
        assertParseFailure(parser, "a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "R", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " ///", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " 123", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortContactCommand.MESSAGE_USAGE));
    }
}
