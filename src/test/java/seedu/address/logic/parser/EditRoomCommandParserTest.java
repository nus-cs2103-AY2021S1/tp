package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditRoomCommand;

public class EditRoomCommandParserTest {
    private EditRoomCommandParser parser = new EditRoomCommandParser();

    @Test
    public void parse_validArgs_returnsEditRoomCommand() {
        assertParseSuccess(parser, " 1 1",
            new EditRoomCommand(1, 1));
        assertParseSuccess(parser, " 1 10",
            new EditRoomCommand(1, 10));
        assertParseSuccess(parser, " 1 100",
            new EditRoomCommand(1, 100));
        assertParseSuccess(parser, " 1 1000",
            new EditRoomCommand(1, 1000));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.MESSAGE_USAGE));
    }
}
