package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DAILY_DELETE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DAILY_DELETE_DESC;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.DailyCommand;

/**
 * Contains integration tests for {@code DailyCommandParser}.
 */
public class DailyCommandParserTest {

    private static final String MESSAGE_INVALID_SWITCH_TAB_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DailyCommand.MESSAGE_SWITCH_TAB_USAGE);
    private final DailyCommandParser parser = new DailyCommandParser();
    private final DailyCommand dailyCommand = new DailyCommand();

    @Test
    public void parse_switchDailyTab_success() {
        assertParseSuccess(parser, "", dailyCommand);
    }

    @Test
    public void parse_switchDailyTab_failure() {
        assertParseFailure(parser, "dailyyy", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
    }

    @Test
    public void parse_deleteIndexValid_success() {
        String userInput = DAILY_DELETE_DESC_1;

        assertParseSuccess(parser, userInput, new DailyCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_deleteIndexInvalid_failure() {
        String userInput = INVALID_DAILY_DELETE_DESC;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }
}
