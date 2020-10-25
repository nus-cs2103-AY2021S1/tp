package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.storage.JsonAdaptedRoom.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalRooms.ROOM_ONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;

class JsonAdaptedRoomTest {
    private static final String INVALID_FLOOR = "08";
    private static final String INVALID_NUMBER = "0110";
    private static final String INVALID_ROOMTYPE = "??";
    private static final String INVALID_TAG = "#toilet";

    private static final String VALID_FLOOR = ROOM_ONE.getFloor().toString();
    private static final String VALID_NUMBER = ROOM_ONE.getRoomNumber().toString();
    private static final String VALID_ROOMTYPE = ROOM_ONE.getRoomType().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ROOM_ONE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    void toModelType_validRoomDetails_returnsRoom() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_ONE);
        assertEquals(ROOM_ONE, room.toModelType());
    }

    @Test
    public void toModelType_invalidFloor_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(INVALID_FLOOR, VALID_NUMBER, VALID_ROOMTYPE, VALID_TAGS);
        String expectedMessage = Floor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_nullFloor_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(null, VALID_NUMBER, VALID_ROOMTYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Floor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidNumber_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(VALID_FLOOR, INVALID_NUMBER, VALID_ROOMTYPE, VALID_TAGS);
        String expectedMessage = RoomNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_nullNumber_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(VALID_FLOOR, null, VALID_ROOMTYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidRoomType_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(VALID_FLOOR, VALID_NUMBER, INVALID_ROOMTYPE, VALID_TAGS);
        String expectedMessage = RoomType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_nullRoomType_throwsIllegalValueException() {
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(VALID_FLOOR, VALID_NUMBER, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRoom room =
                new JsonAdaptedRoom(VALID_FLOOR, VALID_NUMBER, VALID_ROOMTYPE, invalidTags);
        assertThrows(IllegalValueException.class, room::toModelType);
    }

}
