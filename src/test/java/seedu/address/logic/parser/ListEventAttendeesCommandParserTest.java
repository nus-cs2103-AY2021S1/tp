package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListEventAttendeesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListEventAttendeesCommandParserTest {
    private ListEventAttendeesCommandParser parser = new ListEventAttendeesCommandParser();

    @Test
    public void parse_validArgs_returnsListEventAttendeesCommand() throws ParseException {
        assertParseSuccess(parser, " 1",
                new ListEventAttendeesCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_validArgsWithSpace_returnsListEventAttendeesCommand() throws ParseException {
        assertParseSuccess(parser, "     1        ",
                new ListEventAttendeesCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListEventAttendeesCommand.MESSAGE_USAGE));
    }
}
