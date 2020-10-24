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

import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ListRoomCommand.RoomFilter;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.testutil.RoomFilterBuilder;

class ListRoomCommandParserTest {

    private static final String MESSAGE_INVALID_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.HELP.getFullMessage());

    private final ListRoomCommandParser parser = new ListRoomCommandParser();

    @Test
    void parse_whitespaceOnlyArg_returnsListRoomCommand() {
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(new RoomFilter());

        // empty input
        assertParseSuccess(parser, "", expectedListRoomCommand);

        // whitespace
        assertParseSuccess(parser, "            ", expectedListRoomCommand);

        // tabs
        assertParseSuccess(parser, "\t", expectedListRoomCommand);

        // tabs
        assertParseSuccess(parser, "         \t       ", expectedListRoomCommand);
    }

    @Test
    void parse_validVacantCommand_returnsListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.onlyVacant();
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_KEYWORD + ListRoomCommand.COMMAND_VACANT_FLAG,
                expectedListRoomCommand);
    }

    @Test
    void parse_validAllocatedCommand_returnsListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.onlyAllocated();
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_KEYWORD + ListRoomCommand.COMMAND_ALLOCATED_FLAG,
                expectedListRoomCommand);
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
        assertParseFailure(parser, PREFIX_KEYWORD + ListRoomCommand.COMMAND_ALLOCATED_FLAG + " "
                + PREFIX_KEYWORD + ListRoomCommand.COMMAND_VACANT_FLAG, MESSAGE_INVALID_COMMAND);
    }

    // Floor

    @Test
    void parse_validFloor_returnListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.addFloors(Arrays.asList(new Floor(VALID_FLOOR_A)));
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A, expectedListRoomCommand);
    }

    @Test
    void parse_validFloors_returnListRoomCommand() {
        RoomFilter filter = new RoomFilter();
        filter.addFloors(Arrays.asList(new Floor(VALID_FLOOR_A), new Floor(VALID_FLOOR_B)));
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_B,
                expectedListRoomCommand);
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
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_A, expectedListRoomCommand);
    }

    @Test
    void parse_validRoomNumbers_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder()
                .addRoomNumber(VALID_ROOM_NUMBER_A).addRoomNumber(VALID_ROOM_NUMBER_B).build();
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_A
                + " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_B, expectedListRoomCommand);
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
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A, expectedListRoomCommand);
    }

    @Test
    void parse_validRoomTypes_returnListRoomCommand() {
        RoomFilter filter = new RoomFilterBuilder()
                .addRoomType(VALID_ROOM_TYPE_A).addRoomType(VALID_ROOM_TYPE_B).build();
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A
                + " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_B, expectedListRoomCommand);
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
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(filter);
        assertParseSuccess(parser, " " + PREFIX_ROOM_TYPE + VALID_ROOM_TYPE_A + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_A
                + " " + PREFIX_ROOM_FLOOR + VALID_FLOOR_B + " " + PREFIX_KEYWORD + ListRoomCommand.COMMAND_VACANT_FLAG,
                expectedListRoomCommand);
    }
}
