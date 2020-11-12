package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DURATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DURATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODEL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;


public class DoneCommandParserTest {

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_validArgs_returnsDoneCommand() {
        Index[] indexes = {INDEX_FIRST_MODEL};
        int[] durations = {INDEX_FIRST_DURATION};
        assertParseSuccess(parser, " 1:10", new DoneCommand(indexes, durations));
    }

    @Test
    public void parse_manyValidArgs_returnsDoneCommand() {
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_SECOND_MODEL};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};
        assertParseSuccess(parser, " 1:10 2:20", new DoneCommand(indexes, durations));
    }

    @Test
    public void parse_manySpaceBetweenValidArgs_returnsDoneCommand() {
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_SECOND_MODEL};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};
        assertParseSuccess(parser, " \n 1:10 \t\t\t 2:20 \n ", new DoneCommand(indexes, durations));
    }

    @Test
    public void parse_invalidDurationArg_throwsParseException() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            Messages.INVALID_DURATION_FORMAT, DoneCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 1:-1", expectedMessage);
    }

    @Test
    public void parse_invalidDurationAmongManyArgs_throwsParseException() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.INVALID_DURATION_FORMAT, DoneCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 1:10 2:0 3:40 5:50", expectedMessage);
    }
}
