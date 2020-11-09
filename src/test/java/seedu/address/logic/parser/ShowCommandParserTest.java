package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertShowParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertShowParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.ShowCommand;

public class ShowCommandParserTest {
    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_validArgs_returnsShowCommand() {
        assertShowParseSuccess(parser, " -activity", new ShowCommand("activity"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertShowParseFailure(parser, " -activity asd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }
}
