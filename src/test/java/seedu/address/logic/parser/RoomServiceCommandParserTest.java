package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_SERVICE_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RoomServiceCommand;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

class RoomServiceCommandParserTest {

    private RoomServiceCommandParser parser = new RoomServiceCommandParser();

    @Test
    void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoomServiceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + RoomServiceType.WIFI.getName();
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));

        String input2 = " " + PREFIX_ROOM_SERVICE_TYPE
                + RoomServiceType.DINING.getName() + " " + PREFIX_BOOKING_ID + "1";
        assertParseSuccess(
                parser, input2, new RoomServiceCommand(new RoomService(1, RoomServiceType.DINING)));

        String input3 = " " + PREFIX_ROOM_SERVICE_TYPE
                + RoomServiceType.MASSAGE.getName() + " " + PREFIX_BOOKING_ID + "1";
        assertParseSuccess(
                parser, input3, new RoomServiceCommand(new RoomService(1, RoomServiceType.MASSAGE)));
    }

    @Test
    public void parse_validArgsDifferentCasesSuccess() {
        String wifiVariant1 = "wiFi";
        String wifiVariant2 = "wIFi";
        String wifiVariant3 = "WifI";
        String diningVariant1 = "diNinG";
        String diningVariant2 = "DINing";
        String diningVariant3 = "dinINg";
        String massageVariant1 = "mASSaGE";
        String massageVariant2 = "mASsaGe";
        String massageVariant3 = "MASSage";

        String input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + wifiVariant1;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + wifiVariant2;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + wifiVariant3;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + diningVariant1;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.DINING)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + diningVariant2;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.DINING)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + diningVariant3;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.DINING)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + massageVariant1;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.MASSAGE)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + massageVariant2;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.MASSAGE)));

        input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + massageVariant3;
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.MASSAGE)));
    }

    @Test
    public void parse_invalidRoomServiceTypeOne_throwsParseException() {
        String input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + "Escorts";
        assertParseFailure(parser, input, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoomServiceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRoomServiceTwo_throwsParseException() {
        String input = " " + PREFIX_BOOKING_ID + "2 " + PREFIX_ROOM_SERVICE_TYPE + "wife";
        assertParseFailure(parser, input, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoomServiceCommand.MESSAGE_USAGE));
    }
}
