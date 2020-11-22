package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditBlockCommand;

public class EditBlockCommandParserTest {
    private EditBlockCommandParser parser = new EditBlockCommandParser();

    @Test
    public void parse_validArgs_returnsEditBlockCommand() {
        assertParseSuccess(parser, "A Z",
            new EditBlockCommand('A', 'Z'));
        assertParseSuccess(parser, "A M",
            new EditBlockCommand('A', 'M'));
        assertParseSuccess(parser, "N Z",
            new EditBlockCommand('N', 'Z'));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBlockCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "A", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBlockCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "AA D", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBlockCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "A DD", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBlockCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "A D F", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBlockCommand.MESSAGE_USAGE));
    }
}
