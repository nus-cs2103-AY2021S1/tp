package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.command.PatientCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.testutil.command.PatientCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.TEMP_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER_DESC;
import static seedu.address.testutil.command.RoomCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.SearchRoomCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.patient.Name;
import seedu.address.testutil.SearchRoomDescriptorBuilder;

//@@author chiamyunqing
/**
 * Contains unit test for SearchRoomCommandParser.
 */
public class SearchRoomCommandParserTest {

    private SearchRoomCommandParser parser = new SearchRoomCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));

        //name and room number prefixes not found
        assertParseFailure(parser, TEMP_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));

        //multiple prefixes found
        assertParseFailure(parser, NAME_DESC_AMY + ROOM_NUMBER_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        //invalid patient name prefix
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        //invalid room number prefix
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER_DESC,
                String.format(ParserUtil.MESSAGE_INVALID_UNSIGNED_INT, RoomCliSyntax.PREFIX_ROOM_NUMBER,
                INVALID_NON_NUMBER_ROOM_NUMBER));
    }

    @Test
    public void parse_validInput_success() {
        //find by patient name
        SearchRoomDescriptorBuilder descriptorName = new SearchRoomDescriptorBuilder();
        descriptorName.setPatientName(VALID_NAME_AMY);
        assertParseSuccess(parser, NAME_DESC_AMY, new SearchRoomCommand(descriptorName.build()));
        //find by room number
        SearchRoomDescriptorBuilder descriptorRoomNum = new SearchRoomDescriptorBuilder();
        descriptorRoomNum.setRoomNumber(Integer.parseInt(VALID_ROOM_NUMBER_ONE));
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE, new SearchRoomCommand(descriptorRoomNum.build()));
    }
}
