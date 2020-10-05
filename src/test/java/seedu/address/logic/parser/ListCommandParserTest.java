package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "case", new ListCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "case 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE));
    }
}
