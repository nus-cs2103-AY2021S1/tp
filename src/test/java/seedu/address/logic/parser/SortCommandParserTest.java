package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {

        SortCommand expectedSortCommandName = new SortCommand("name");
        SortCommand expectedSortCommandClassTime = new SortCommand("classTime");
        SortCommand expectedSortCommandYear = new SortCommand("year");
        assertParseSuccess(parser, "classTime", expectedSortCommandClassTime);

        // multiple whitespaces before keywords
        assertParseSuccess(parser, "       name", expectedSortCommandName);

        // a lot of whitespace
        assertParseSuccess(parser, "       year     ", expectedSortCommandYear);
    }

    @Test
    public void parse_invalidParameters_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // multiple parameters
        assertParseFailure(parser, "name year", expectedMessage);

        // not a recognised parameter
        assertParseFailure(parser, "class", expectedMessage);

        // duplicate parameters
        assertParseFailure(parser, "name name", expectedMessage);

        // white space given
        assertParseFailure(parser, "     ", expectedMessage);
    }

}
