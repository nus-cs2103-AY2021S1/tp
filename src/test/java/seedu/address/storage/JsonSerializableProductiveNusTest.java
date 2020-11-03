package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ProductiveNus;
import seedu.address.testutil.TypicalAssignments;

public class JsonSerializableProductiveNusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableProductiveNusTest");
    private static final Path TYPICAL_ASSIGNMENTS_FILE = TEST_DATA_FOLDER
            .resolve("typicalAssignmentsProductiveNus.json");
    private static final Path INVALID_ASSIGNMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAssignmentProductiveNus.json");
    private static final Path DUPLICATE_ASSIGNMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateAssignmentProductiveNus.json");

    @Test
    public void toModelType_typicalAssignmentsFile_success() throws Exception {
        JsonSerializableProductiveNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_ASSIGNMENTS_FILE,
                JsonSerializableProductiveNus.class).get();
        ProductiveNus productiveNusFromFile = dataFromFile.toModelType();
        ProductiveNus typicalAssignmentsProductiveNus = TypicalAssignments.getTypicalProductiveNus();
        assertEquals(productiveNusFromFile, typicalAssignmentsProductiveNus);
    }

    @Test
    public void toModelType_invalidAssignmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProductiveNus dataFromFile = JsonUtil.readJsonFile(INVALID_ASSIGNMENT_FILE,
                JsonSerializableProductiveNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAssignments_throwsIllegalValueException() throws Exception {
        JsonSerializableProductiveNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ASSIGNMENT_FILE,
                JsonSerializableProductiveNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableProductiveNus.MESSAGE_DUPLICATE_ASSIGNMENT,
                dataFromFile::toModelType);
    }

}
