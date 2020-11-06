package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.commons.util.JsonUtil;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.testutil.TypicalSerialNumberSets;

public class JsonSerializableSerialNumberSetsBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSerialNumberSetsBookTest");
    private static final Path TYPICAL_STOCKS_FILE = TEST_DATA_FOLDER.resolve("typicalSerialNumberSets.json");
    private static final Path INVALID_STOCK_FILE = TEST_DATA_FOLDER.resolve("invalidSerialNumberSets.json");
    private static final Path DUPLICATE_STOCK_FILE = TEST_DATA_FOLDER.resolve("duplicateSerialNumberSets.json");

    @Test
    public void toModelType_typicalSerialNumberSetssFile_success() throws Exception {
        JsonSerializableSerialNumberSetsBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STOCKS_FILE,
                JsonSerializableSerialNumberSetsBook.class).get();
        SerialNumberSetsBook serialNumberSetsBookFromFile = dataFromFile.toModelType();
        SerialNumberSetsBook typicalSerialNumberSetsSerialNumberSetsBook =
                TypicalSerialNumberSets.getTypicalSerialNumberSetsBook();
        assertEquals(serialNumberSetsBookFromFile, typicalSerialNumberSetsSerialNumberSetsBook);
    }

    @Test
    public void toModelType_invalidSerialNumberSetsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSerialNumberSetsBook dataFromFile = JsonUtil.readJsonFile(INVALID_STOCK_FILE,
                JsonSerializableSerialNumberSetsBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSerialNumberSetss_throwsIllegalValueException() throws Exception {
        JsonSerializableSerialNumberSetsBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STOCK_FILE,
                JsonSerializableSerialNumberSetsBook.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableSerialNumberSetsBook.MESSAGE_DUPLICATE_SERIAL_NUMBER_SET,
                dataFromFile::toModelType);
    }
}
