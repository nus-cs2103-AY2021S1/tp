package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_FLOOR;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_TYPE;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.ListRoomsCommand;
import seedu.resireg.logic.commands.ListRoomsCommand.RoomFilter;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.testutil.RoomFilterBuilder;

class ListRoomsCommandParserTest {

    private static final String MESSAGE_INVALID_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomsCommand.HELP.getFullMessage());

    private final ListRoomsCommandParser parser = new ListRoomsCommandParser();

    @Test
    void parse_whitespaceOnlyArg_returnsListRoomCommand() {
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(new RoomFilter());

        // empty input
        assertParseSuccess(parser, "", expectedListRoomsCommand);

        // whitespace
        assertParseSuccess(parser, "            ", expectedListRoomsCommand);

        // tabs
        assertParseSuccess(parser, "\t", expectedListRoomsCommand);

        // tabs
        assertParseSuccess(parser, "         \t       ", expectedListRoomsCommand);
    }

    @Test
    void parse_validVacantCommand_returnsListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.onlyVacant();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_KEYWORD + ListRoomsCommand.COMMAND_VACANT_FLAG,
                expectedListRoomsCommand);
    }

    @Test
    void parse_validAllocatedCommand_returnsListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.onlyAllocated();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_KEYWORD + ListRoomsCommand.COMMAND_ALLOCATED_FLAG,
                expectedListRoomsCommand);
    }

    @Test
    void parse_invalidVacancyFlag_throwsParseException() {
        // missing flag
        assertParseFailure(parser, " " + PREFIX_KEYWORD.getPrefix(), MESSAGE_INVALID_COMMAND);
        // no two dashes
        assertParseFailure(parser, " -", MESSAGE_INVALID_COMMAND);
        // misspelled vacant
        assertParseFailure(parser, " --vacent", MESSAGE_INVALID_COMMAND);
        // misspelled allocated
        assertParseFailure(parser, " -allokated", MESSAGE_INVALID_COMMAND);
    }

    @Test
    void parse_bothVacantAndAllocated_throwsParseException() {
        assertParseFailure(parser, PREFIX_KEYWORD + ListRoomsCommand.COMMAND_ALLOCATED_FLAG + " "
                + PREFIX_KEYWORD + ListRoomsCommand.COMMAND_VACANT_FLAG, MESSAGE_INVALID_COMMAND);
    }

    // Floor

    @Test
    void parse_validFloor_returnListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.addFloors(Arrays.asList(new Floor(VALID_FLOOR_A)));
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A, expectedListRoomsCommand);
    }

    @Test
    void parse_validFloors_returnListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.addFloors(Arrays.asList(new Floor(VALID_FLOOR_A), new Floor(VALID_FLOOR_B)));
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_B,
                expectedListRoomsCommand);
    }

    @Test
    void parse_invalidFloor_throwsCommandException() {
        assertParseFailure(parser, " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A + " " + PREFIX_ROOM_FLOOR + INVALID_FLOOR,
                Floor.MESSAGE_CONSTRAINTS);
    }

    // RoomNumber

    @Test
    void parse_validRoomNumber_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder().addRoomNumber(VALID_ROOM_NUMBER_A).build();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_A, expectedListRoomsCommand);
    }

    @Test
    void parse_validRoomNumbers_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder()
                .addRoomNumber(VALID_ROOM_NUMBER_A).addRoomNumber(VALID_ROOM_NUMBER_B).build();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_A
                + " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_B, expectedListRoomsCommand);
    }

    @Test
    void parse_invalidRoomNumber_throwsCommandException() {
        assertParseFailure(parser, " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_A
                        + " " + PREFIX_ROOM_NUMBER + INVALID_ROOM_NUMBER, RoomNumber.MESSAGE_CONSTRAINTS);
    }

    // RoomType

    @Test
    void parse_validRoomType_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder().addRoomType(VALID_ROOM_TYPE_A).build();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A, expectedListRoomsCommand);
    }

    @Test
    void parse_validRoomTypes_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder()
                .addRoomType(VALID_ROOM_TYPE_A).addRoomType(VALID_ROOM_TYPE_B).build();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A
                + " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_B, expectedListRoomsCommand);
    }

    @Test
    void parse_invalidRoomType_throwsCommandException() {
        assertParseFailure(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A
                + " " + PREFIX_ROOM_TYPE + INVALID_ROOM_TYPE, RoomType.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_multipleFilterTypes_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder()
                .onlyVacant().addRoomType(VALID_ROOM_TYPE_A).addFloor(VALID_FLOOR_A).addFloor(VALID_FLOOR_B).build();
        ListRoomsCommand expectedListRoomsCommand = new ListRoomsCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A
                + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_B + " " + PREFIX_KEYWORD + ListRoomsCommand.COMMAND_VACANT_FLAG,
                expectedListRoomsCommand);
    }
}
