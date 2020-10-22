package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "5", new ListCommand(Index.fromZeroBased(5)));
        assertParseSuccess(parser, "2", new ListCommand(Index.fromZeroBased(2)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "0", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "%", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
