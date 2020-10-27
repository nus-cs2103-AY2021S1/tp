package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.PINEAPPLE;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.StockBook;

public class JsonStockBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStockBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStockBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStockBook(null));
    }

    private java.util.Optional<ReadOnlyStockBook> readStockBook(String filePath) throws Exception {
        return new JsonStockBookStorage(Paths.get(filePath)).readStockBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStockBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readStockBook("notJsonFormatStockBook.json"));
    }

    @Test
    public void readStockBook_invalidStockStockBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStockBook("invalidStockStockBook.json"));
    }

    @Test
    public void readStockBook_invalidAndValidStockStockBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStockBook("invalidAndValidStockStockBook.json"));
    }

    @Test
    public void readAndSaveStockBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStockBook.json");
        StockBook original = getTypicalStockBook();
        JsonStockBookStorage jsonStockBookStorage = new JsonStockBookStorage(filePath);

        // Save in new file and read back
        jsonStockBookStorage.saveStockBook(original, filePath);
        ReadOnlyStockBook readBack = jsonStockBookStorage.readStockBook(filePath).get();
        assertEquals(original, new StockBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStock(PINEAPPLE);
        original.removeStock(APPLE);
        jsonStockBookStorage.saveStockBook(original, filePath);
        readBack = jsonStockBookStorage.readStockBook(filePath).get();
        assertEquals(original, new StockBook(readBack));

        // Save and read without specifying file path
        original.addStock(APPLE);
        jsonStockBookStorage.saveStockBook(original); // file path not specified
        readBack = jsonStockBookStorage.readStockBook().get(); // file path not specified
        assertEquals(original, new StockBook(readBack));

    }

    @Test
    public void saveStockBook_nullStockBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStockBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code stockBook} at the specified {@code filePath}.
     */
    private void saveStockBook(ReadOnlyStockBook stockBook, String filePath) {
        try {
            new JsonStockBookStorage(Paths.get(filePath))
                    .saveStockBook(stockBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStockBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStockBook(new StockBook(), null));
    }
}
