package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.ClearCommand;

public class ClearCommandParserTest {

    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_validInput_returnsClearCommand() {
        String input = "";

        ClearCommand expectedClearCommandCommand = new ClearCommand();

        assertParseSuccess(parser, input, expectedClearCommandCommand);
    }

    @Test
    public void parse_onlyWhiteSpaceArgs_returnsClearCommand() {
        String input = "                   ";

        ClearCommand expectedClearCommandCommand = new ClearCommand();

        assertParseSuccess(parser, input, expectedClearCommandCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "MoreThanLengthZeroArgument",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }
}
