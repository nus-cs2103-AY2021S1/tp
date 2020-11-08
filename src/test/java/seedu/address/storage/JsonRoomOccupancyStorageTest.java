package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_1;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_2;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.RoomList;
import seedu.address.model.room.Room;

class JsonRoomOccupancyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonRoomOccupancyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRoomList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRoomList(null));
    }
    private Optional<ReadOnlyList<Room>> readRoomList(String filePath) throws Exception {
        return new JsonRoomOccupancyStorage(Paths.get(filePath))
                .readOnlyRoomOccupancy(addToTestDataPathIfNotNull(filePath));
    }
    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }
    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRoomList("NonExistentFile.json").isPresent());
    }
    @Test
    public void readCovigentApp_invalidRoomCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoomList("invalidRoomInRoomList.json"));
    }
    @Test
    public void readCovigentApp_invalidAndValidRoomCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoomList("invalidAndValidRoomInRoomList.json"));
    }
    @Test
    public void saveRoomList_nullCovigentApp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(null,
                "roomsOccupied.jason"));
    }

    @Test
    public void saveRoomList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(new RoomList(), null));
    }

    /**
     * Saves {@code covigentApp} at the specified {@code filePath}.
     */
    private void saveRoomListRoomsOccupied(RoomList roomList, String roomsOccupied) {
        try {
            new JsonRoomOccupancyStorage(Paths.get(roomsOccupied))
                    .saveOccupiedRooms(roomList, addToTestDataPathIfNotNull(roomsOccupied));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void readAndSaveCovigentApp_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCovigentApp.json");
        RoomList original = getTypicalRoomList();
        JsonRoomOccupancyStorage jsonRoomOccupancyStorage = new JsonRoomOccupancyStorage(filePath);

        // Save in new file and read back
        jsonRoomOccupancyStorage.saveOccupiedRooms(original, filePath);
        ReadOnlyList<Room> readBack = jsonRoomOccupancyStorage.readOnlyRoomOccupancy(filePath).get();
        RoomList roomList = new RoomList(readBack);
        assertEquals(original, roomList);

        // Modify data, overwrite exiting file, and read back
        original.addRooms(ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_1);
        jsonRoomOccupancyStorage.saveOccupiedRooms(original, filePath);
        readBack = jsonRoomOccupancyStorage.readOnlyRoomOccupancy(filePath).get();
        assertEquals(original, new RoomList(readBack));

        // Save and read without specifying file path
        original.addRooms(ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_2);
        jsonRoomOccupancyStorage.saveOccupiedRooms(original); // file path not specified
        readBack = jsonRoomOccupancyStorage.readOnlyRoomOccupancy().get(); // file path not specified
        assertEquals(original, new RoomList(readBack));

    }

    @Test
    public void saveCovigentApp_nullCovigentApp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoomListRoomsOccupied(null, "SomeFile.json"));
    }

}
