package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.ClearStatsCommand;

public class ClearStatsCommandParserTest {
    private final ClearStatsCommandParser parser = new ClearStatsCommandParser();

    @Test
    public void parse_validArgs_returnsOpenCommand() {
        assertParseSuccess(parser, "1", new ClearStatsCommand(INDEX_FIRST_FLASHCARD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearStatsCommand.MESSAGE_USAGE));
    }
}
