package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;

//author zeying99

public class ExitCommandParserTest {

    private ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_trailingInvalidArgs_failure() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validEmptyArgs_returnsExitCommand() {
        ExitCommand expectedClearCommand = new ExitCommand();
        assertParseSuccess(parser, "", expectedClearCommand);
    }

    @Test
    public void parse_validLongSpaceArgs_returnsExitCommand() {
        ExitCommand expectedClearCommand = new ExitCommand();
        assertParseSuccess(parser, "     ", expectedClearCommand);
    }

}
