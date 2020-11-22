package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AssignCommandParserTest {
    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() throws ParseException {
        assertParseSuccess(parser, " 1 1",
                new AssignCommand(ParserUtil.parseIndex("1"),
                ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_validArgsWithSpace_returnsAssignCommand() throws ParseException {
        assertParseSuccess(parser, "     1        1       ",
                new AssignCommand(ParserUtil.parseIndex("1"),
                        ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
    }
}
