package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.commons.util.JsonUtil;
import seedu.stock.model.StockBook;
import seedu.stock.testutil.TypicalStocks;

public class JsonSerializableStockBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStockBookTest");
    private static final Path TYPICAL_STOCKS_FILE = TEST_DATA_FOLDER.resolve("typicalStockStockBook.json");
    private static final Path INVALID_STOCK_FILE = TEST_DATA_FOLDER.resolve("invalidStockStockBook.json");
    private static final Path DUPLICATE_STOCK_FILE = TEST_DATA_FOLDER.resolve("duplicateStockStockBook.json");

    @Test
    public void toModelType_typicalStocksFile_success() throws Exception {
        JsonSerializableStockBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STOCKS_FILE,
                JsonSerializableStockBook.class).get();
        StockBook stockBookFromFile = dataFromFile.toModelType();
        StockBook typicalStocksStockBook = TypicalStocks.getTypicalStockBook();
        assertEquals(stockBookFromFile, typicalStocksStockBook);
    }

    @Test
    public void toModelType_invalidStockFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStockBook dataFromFile = JsonUtil.readJsonFile(INVALID_STOCK_FILE,
                JsonSerializableStockBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStocks_throwsIllegalValueException() throws Exception {
        JsonSerializableStockBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STOCK_FILE,
                JsonSerializableStockBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStockBook.MESSAGE_DUPLICATE_STOCK,
                dataFromFile::toModelType);
    }

}
