package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.room.InitRoomCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.room.InitRoomCommandParser.NUMBER_OF_ROOMS_GIVEN_IN_DIGITS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.InitRoomCommand;

class InitRoomParserTest {
    private InitRoomCommandParser parser = new InitRoomCommandParser();
    @Test
    void parse() {
        String input = "123";
        assertParseSuccess(parser, input, new InitRoomCommand(123));
    }

    @Test
    void parseError_empty_input() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    void parse_error() {
        assertParseFailure(parser, "two hundred",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
        assertParseFailure(parser, "twelve",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
    }
}
