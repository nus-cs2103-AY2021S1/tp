package seedu.tasklist.logic.parser;

import static seedu.tasklist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tasklist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.logic.commands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "5", new ListCommand(Index.fromZeroBased(5)));
        assertParseSuccess(parser, "2", new ListCommand(Index.fromZeroBased(2)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "0", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "a", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "%", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);
    }
}
