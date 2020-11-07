package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "1", new ListCommand(Index.fromZeroBased(1)));
        assertParseSuccess(parser, "25", new ListCommand(Index.fromZeroBased(25)));
        assertParseSuccess(parser, "50", new ListCommand(Index.fromZeroBased(50)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "0", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "51", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "a", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);

        assertParseFailure(parser, "%", ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);
    }
}
