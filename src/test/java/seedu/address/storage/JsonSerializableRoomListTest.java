package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.RoomList;
import seedu.address.testutil.TypicalRooms;


//@@author
class JsonSerializableRoomListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRoomListTest");
    private static final Path TYPICAL_ROOM_LIST = TEST_DATA_FOLDER.resolve("typicalRoomsInRoomList.json");
    private static final Path WRONG_ORDER_ROOM_LIST = TEST_DATA_FOLDER.resolve("notCorrectOrder.json");
    private static final Path START_ROOM_NUMBER_NOT_ONE = TEST_DATA_FOLDER.resolve("notCorrectStartRooms.json");

    @Test
    public void toModelType_typicalRoomFile_success() throws Exception {
        JsonSerializableRoomList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ROOM_LIST,
                JsonSerializableRoomList.class).get();
        RoomList covigentAppFromFile = dataFromFile.toModelType();
        RoomList typicalRoomList = TypicalRooms.getTypicalRoomList();
        assertEquals(covigentAppFromFile, typicalRoomList);
    }

    @Test
    public void toModelType_roomListWrongOrder_failure() throws Exception {
        JsonSerializableRoomList dataFromFile = JsonUtil.readJsonFile(WRONG_ORDER_ROOM_LIST,
                JsonSerializableRoomList.class).get();
        String expectedMessage = JsonSerializableRoomList.WRONG_ORDER_OF_ROOM;
        assertThrows(IllegalValueException.class, expectedMessage, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_roomListWrongStartNumber_failure() throws Exception {
        JsonSerializableRoomList dataFromFile = JsonUtil.readJsonFile(START_ROOM_NUMBER_NOT_ONE,
                JsonSerializableRoomList.class).get();
        String expectedMessage = JsonSerializableRoomList.WRONG_ORDER_OF_ROOM;
        assertThrows(IllegalValueException.class, expectedMessage, dataFromFile::toModelType);
    }
}


