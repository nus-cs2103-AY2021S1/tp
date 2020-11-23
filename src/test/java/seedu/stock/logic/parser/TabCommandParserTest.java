package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.TabCommand;

public class TabCommandParserTest {

    private TabCommandParser parser = new TabCommandParser();

    @Test
    public void parse_validInput_returnsTabCommand() {
        String input = "";

        TabCommand expectedTabCommandCommand = new TabCommand();

        assertParseSuccess(parser, input, expectedTabCommandCommand);
    }

    @Test
    public void parse_onlyWhiteSpaceArgs_returnsTabCommand() {
        String input = "                   ";

        TabCommand expectedTabCommandCommand = new TabCommand();

        assertParseSuccess(parser, input, expectedTabCommandCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "MoreThanLengthZeroArgument",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
    }
}
