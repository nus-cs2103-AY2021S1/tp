package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.FLOOR_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.FLOOR_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_FLOOR_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_TYPE_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_TYPE_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_TYPE_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_RENOVATED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_RENOVATED;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalRooms.ROOM_A;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.AddRoomCommand;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.testutil.RoomBuilder;

public class AddRoomCommandParserTest {
    private final AddRoomCommandParser parser = new AddRoomCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Room expectedRoom = new RoomBuilder(ROOM_A).withTags(VALID_TAG_RENOVATED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A
                + TAG_DESC_RENOVATED, new AddRoomCommand(expectedRoom));

        // multiple floors - last floor accepted
        assertParseSuccess(parser, FLOOR_DESC_B + FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A
                + TAG_DESC_RENOVATED, new AddRoomCommand(expectedRoom));

        // mutiple room numbers - last room number accepted
        assertParseSuccess(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_B + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A
                + TAG_DESC_RENOVATED, new AddRoomCommand(expectedRoom));

        // multiple room types - last room type accepted
        assertParseSuccess(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_B + ROOM_TYPE_DESC_A
                + TAG_DESC_RENOVATED, new AddRoomCommand(expectedRoom));

        // multiple tags - all accepeted
        Room expectedRoomMultipleTags = new RoomBuilder(ROOM_A).withTags(VALID_TAG_DAMAGED, VALID_TAG_RENOVATED)
                .build();
        assertParseSuccess(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A + TAG_DESC_RENOVATED
                + TAG_DESC_DAMAGED, new AddRoomCommand(expectedRoomMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no tags
        Room expectedRoom = new RoomBuilder(ROOM_A).withTags().build();
        assertParseSuccess(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A,
                new AddRoomCommand(expectedRoom));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.HELP.getFullMessage());

        // missing floor prefix
        assertParseFailure(parser, " " + VALID_FLOOR_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A, expectedMessage);

        // missing room number prefix
        assertParseFailure(parser, FLOOR_DESC_A + " " + VALID_ROOM_NUMBER_A + ROOM_TYPE_DESC_A, expectedMessage);

        // missing room type prefix
        assertParseFailure(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + " " + VALID_ROOM_TYPE_A, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + VALID_FLOOR_A + " " + VALID_ROOM_NUMBER_A + " " + VALID_ROOM_TYPE_A,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid floor
        assertParseFailure(parser, INVALID_FLOOR_DESC + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A + TAG_DESC_RENOVATED,
                Floor.MESSAGE_CONSTRAINTS);

        // invalid room number
        assertParseFailure(parser, FLOOR_DESC_A + INVALID_ROOM_NUMBER_DESC + ROOM_TYPE_DESC_A + TAG_DESC_RENOVATED,
                RoomNumber.MESSAGE_CONSTRAINTS);

        // invalid room type
        assertParseFailure(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + INVALID_ROOM_TYPE_DESC + TAG_DESC_RENOVATED,
                RoomType.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // 2 invalid values, only 1st invalid value reported
        assertParseFailure(parser, INVALID_FLOOR_DESC + ROOM_NUMBER_DESC_A + INVALID_ROOM_TYPE_DESC
                + TAG_DESC_RENOVATED, Floor.MESSAGE_CONSTRAINTS);

        // nonempty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + FLOOR_DESC_A + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A
                + TAG_DESC_RENOVATED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.HELP.getFullMessage()));
    }
}
