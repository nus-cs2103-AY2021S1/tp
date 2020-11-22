package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditFloorCommand;

public class EditFloorCommandParserTest {
    private EditFloorCommandParser parser = new EditFloorCommandParser();

    @Test
    public void parse_validArgs_returnsEditFloorCommand() {
        assertParseSuccess(parser, " 1 1",
            new EditFloorCommand(1, 1));
        assertParseSuccess(parser, " 1 10",
            new EditFloorCommand(1, 10));
        assertParseSuccess(parser, " 1 100",
            new EditFloorCommand(1, 100));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditFloorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditFloorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditFloorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditFloorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditFloorCommand.MESSAGE_USAGE));
    }
}
