package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.commons.util.JsonUtil;
import seedu.resireg.model.ResiReg;
import seedu.resireg.testutil.TypicalStudents;

public class JsonSerializableResiRegTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableResiRegTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsResiReg.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentResiReg.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentResiReg.json");
    private static final Path DUPLICATE_ROOM_FILE = TEST_DATA_FOLDER.resolve("duplicateRoomResiReg.json");
    private static final Path DUPLICATE_ALLOCATION_FILE = TEST_DATA_FOLDER.resolve("duplicateAllocationResiReg.json");
    private static final Path DUPLICATE_BIN_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateBinItemResiReg.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableResiReg.class).get();
        ResiReg resiRegFromFile = dataFromFile.toModelType();
        ResiReg typicalStudentsResiReg = TypicalStudents.getTypicalResiReg();
        assertEquals(resiRegFromFile, typicalStudentsResiReg);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableResiReg.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableResiReg.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResiReg.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRooms_throwsIllegalValueException() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ROOM_FILE,
                JsonSerializableResiReg.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResiReg.MESSAGE_DUPLICATE_ROOM,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAllocations_throwsIllegalValueException() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ALLOCATION_FILE,
                JsonSerializableResiReg.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResiReg.MESSAGE_DUPLICATE_ALLOCATION,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBinItems_throwsIllegalValueException() throws Exception {
        JsonSerializableResiReg dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BIN_ITEM_FILE,
                JsonSerializableResiReg.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResiReg.MESSAGE_DUPLICATE_BIN_ITEM,
                dataFromFile::toModelType);
    }
}
