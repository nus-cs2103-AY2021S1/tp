package seedu.flashcard.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.flashcard.logic.commands.DeleteCommand;
import seedu.flashcard.logic.commands.StatsCommand;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

class StatsCommandParserTest {

    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new StatsCommand(INDEX_FIRST_FLASHCARD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

}