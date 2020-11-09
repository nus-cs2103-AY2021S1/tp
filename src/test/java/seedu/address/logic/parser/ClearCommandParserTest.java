package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;

public class ClearCommandParserTest {

    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_trailingInvalidArgs_failure() {
        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validEmptyArgs_returnsClearCommand() {
        ClearCommand expectedClearCommand = new ClearCommand();
        assertParseSuccess(parser, "", expectedClearCommand);
    }

    @Test
    public void parse_validLongSpaceArgs_returnsClearCommand() {
        ClearCommand expectedClearCommand = new ClearCommand();
        assertParseSuccess(parser, "     ", expectedClearCommand);
    }

}
