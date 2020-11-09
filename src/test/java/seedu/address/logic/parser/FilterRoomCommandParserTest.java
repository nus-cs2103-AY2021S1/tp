package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FilterRoomCommandParserTest {
    private FilterRoomCommandParser parser = new FilterRoomCommandParser();
    private final String startDate = "2024-09-07";
    private final String endDate = "2024-09-11";
    private final String roomType = "1";

    @Test
    public void parse_valueSpecified_success() throws ParseException {
        String userInput1 = START_DATE_DESC_AMY + END_DATE_DESC_AMY + " "
                + PREFIX_ROOM_TYPE + roomType;
        FilterRoomCommand expectedCommand1 = new FilterRoomCommand(VALID_START_DATE_AMY, VALID_END_DATE_AMY, 1);
        assertParseSuccess(parser, userInput1, expectedCommand1);

        String userInput2 = START_DATE_DESC_AMY + END_DATE_DESC_AMY;
        FilterRoomCommand expectedCommand2 = new FilterRoomCommand(VALID_START_DATE_AMY, VALID_END_DATE_AMY, 0);
        assertParseSuccess(parser, userInput2, expectedCommand2);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterRoomCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, FilterRoomCommand.COMMAND_WORD, expectedMessage);

        // no end cDate
        assertParseFailure(parser, FilterRoomCommand.COMMAND_WORD + " "
                    + PREFIX_START_DATE + startDate
                    + PREFIX_ROOM_TYPE + "3",
                expectedMessage);

        // no start Date
        assertParseFailure(parser, FilterRoomCommand.COMMAND_WORD + " "
                + PREFIX_END_DATE + endDate,
                expectedMessage);
    }
}
