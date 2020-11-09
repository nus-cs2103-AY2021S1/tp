package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.EMPTY_TAG_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.FLOOR_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.FLOOR_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_FLOOR_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ROOM_TYPE_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_TYPE_DESC_A;
import static seedu.resireg.logic.commands.CommandTestUtil.ROOM_TYPE_DESC_B;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_RENOVATED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_RENOVATED;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.EditRoomCommand;
import seedu.resireg.logic.commands.EditRoomCommand.EditRoomDescriptor;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.testutil.EditRoomDescriptorBuilder;

public class EditRoomCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditRoomCommand.HELP.getFullMessage());

    private static final String INDEX_STRING = "1";

    private EditRoomCommandParser parser = new EditRoomCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index
        assertParseFailure(parser, FLOOR_DESC_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, INDEX_STRING, EditRoomCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + FLOOR_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + FLOOR_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, INDEX_STRING + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, INDEX_STRING + " j/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid floor
        assertParseFailure(parser, INDEX_STRING + INVALID_FLOOR_DESC, Floor.MESSAGE_CONSTRAINTS);
        // invalid room number
        assertParseFailure(parser, INDEX_STRING + INVALID_ROOM_NUMBER_DESC, RoomNumber.MESSAGE_CONSTRAINTS);
        // invalid room type
        assertParseFailure(parser, INDEX_STRING + INVALID_ROOM_TYPE_DESC, RoomType.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, INDEX_STRING + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INDEX_STRING + INVALID_FLOOR_DESC + INVALID_ROOM_NUMBER_DESC + ROOM_TYPE_DESC_B,
                Floor.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Room} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, INDEX_STRING + TAG_DESC_RENOVATED + TAG_DESC_DAMAGED + EMPTY_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INDEX_STRING + EMPTY_TAG_DESC + TAG_DESC_RENOVATED + TAG_DESC_DAMAGED,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INDEX_STRING + TAG_DESC_RENOVATED + EMPTY_TAG_DESC + TAG_DESC_DAMAGED,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ROOM;
        String userInput = targetIndex.getOneBased() + FLOOR_DESC_B + ROOM_NUMBER_DESC_B + ROOM_TYPE_DESC_B
                + TAG_DESC_RENOVATED;

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_B)
                .withRoomNumber(VALID_ROOM_NUMBER_B).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_RENOVATED)
                .build();
        EditRoomCommand expectedCommand = new EditRoomCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ROOM;
        String userInput = targetIndex.getOneBased() + ROOM_TYPE_DESC_A + FLOOR_DESC_A + ROOM_NUMBER_DESC_A
                + TAG_DESC_RENOVATED + TAG_DESC_DAMAGED + FLOOR_DESC_B + ROOM_TYPE_DESC_B + ROOM_NUMBER_DESC_B;

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_B)
                .withRoomNumber(VALID_ROOM_NUMBER_B).withRoomType(VALID_ROOM_TYPE_B)
                .withTags(VALID_TAG_DAMAGED, VALID_TAG_RENOVATED).build();
        EditRoomCommand expectedCommand = new EditRoomCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ROOM;
        String userInput = targetIndex.getOneBased() + INVALID_ROOM_NUMBER_DESC + ROOM_NUMBER_DESC_A;
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withRoomNumber(VALID_ROOM_NUMBER_A)
                .build();
        EditRoomCommand expectedCommand = new EditRoomCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + FLOOR_DESC_B + INVALID_ROOM_NUMBER_DESC
                + ROOM_NUMBER_DESC_A + ROOM_TYPE_DESC_A;
        descriptor = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_B).withRoomNumber(VALID_ROOM_NUMBER_A)
                .withRoomType(VALID_ROOM_TYPE_A).build();
        expectedCommand = new EditRoomCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + EMPTY_TAG_DESC;

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withTags().build();
        EditRoomCommand expectedCommand = new EditRoomCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
