package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearEventAttendeesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearEventAttendeesCommandParserTest {
    private ClearEventAttendeesCommandParser parser = new ClearEventAttendeesCommandParser();

    @Test
    public void parse_validArgs_returnsClearEventAttendeesCommand() throws ParseException {
        assertParseSuccess(parser, " 1",
                new ClearEventAttendeesCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_validArgsWithSpace_returnsClearEventAttendeesCommand() throws ParseException {
        assertParseSuccess(parser, "     1        ",
                new ClearEventAttendeesCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearEventAttendeesCommand.MESSAGE_USAGE));
    }
}
