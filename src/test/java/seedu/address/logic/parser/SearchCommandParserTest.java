package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_SEARCH_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;

class SearchCommandParserTest {
    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_SEARCH_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        // no leading and trailing whitespaces
        assertParseSuccess(parser, "cs2103t", new SearchCommand(CODE_FIRST_MODULE));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T \n", new SearchCommand(CODE_FIRST_MODULE));
    }
}
