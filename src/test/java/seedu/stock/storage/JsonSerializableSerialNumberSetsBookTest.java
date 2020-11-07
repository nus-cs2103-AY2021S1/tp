package seedu.stock.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.commons.util.JsonUtil;
//import seedu.stock.model.SerialNumberSetsBook;
//import seedu.stock.testutil.TypicalSerialNumberSets;

public class JsonSerializableSerialNumberSetsBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSerialNumberSetsBookTest");
    private static final Path TYPICAL_SERIAL_NUMBER_SETS_FILE =
            TEST_DATA_FOLDER.resolve("typicalSerialNumberSets.json");
    private static final Path INVALID_SERIAL_NUMBER_SET_FILE =
            TEST_DATA_FOLDER.resolve("invalidSerialNumberSets.json");
    private static final Path DUPLICATE_SERIAL_NUMBER_SET_FILE =
            TEST_DATA_FOLDER.resolve("duplicateSerialNumberSets.json");

    @Test
    public void toModelType_invalidSerialNumberSetsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSerialNumberSetsBook dataFromFile = JsonUtil.readJsonFile(INVALID_SERIAL_NUMBER_SET_FILE,
                JsonSerializableSerialNumberSetsBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSerialNumberSets_throwsIllegalValueException() throws Exception {
        JsonSerializableSerialNumberSetsBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SERIAL_NUMBER_SET_FILE,
                JsonSerializableSerialNumberSetsBook.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableSerialNumberSetsBook.MESSAGE_DUPLICATE_SERIAL_NUMBER_SET,
                dataFromFile::toModelType);
    }
}
