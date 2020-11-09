package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddBookingCommandParserTest {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private AddBookingCommandParser parser = new AddBookingCommandParser();
    private final String nonEmptyPersonId = "420";
    private final String nonEmptyRoomId = "2126";
    private final String startDate = "2020-03-15";
    private final String endDate = "2020-03-19";

    @Test
    public void parse_valuesSpecified_success() throws ParseException {
        // have remark
        String userInput = PERSON_ID_DESC_AMY
                + ROOM_ID_DESC_AMY
                + START_DATE_DESC_AMY
                + END_DATE_DESC_AMY;

        logger.info(userInput);

        AddBookingCommand expectedCommand = new AddBookingCommand(VALID_PERSON_ID_AMY,
                VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY,
                VALID_END_DATE_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_datesOutOfOrder_failure() throws ParseException {
        String userInput = PERSON_ID_DESC_AMY
                + ROOM_ID_DESC_AMY
                + START_DATE_DESC_BOB
                + END_DATE_DESC_AMY;

        logger.info(userInput);

        String expectedMessage = String.format(MESSAGE_INVALID_START_END_DATE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddBookingCommand.COMMAND_WORD, expectedMessage);

        // no personId
        assertParseFailure(parser, AddBookingCommand.COMMAND_WORD + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_START_DATE + startDate + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no roomId
        assertParseFailure(parser, AddBookingCommand.COMMAND_WORD + " "
                        + PREFIX_PERSON_ID + nonEmptyPersonId + " "
                        + PREFIX_START_DATE + startDate + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no startDate
        assertParseFailure(parser, AddBookingCommand.COMMAND_WORD + " "
                        + PREFIX_PERSON_ID + nonEmptyPersonId + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no endDate
        assertParseFailure(parser, AddBookingCommand.COMMAND_WORD + " "
                        + PREFIX_PERSON_ID + nonEmptyPersonId + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_START_DATE + startDate,
                expectedMessage);
    }
}
