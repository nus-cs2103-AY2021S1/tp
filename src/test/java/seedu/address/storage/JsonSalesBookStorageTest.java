package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSalesRecordEntries.BSBBT;
import static seedu.address.testutil.TypicalSalesRecordEntries.BSBGT;
import static seedu.address.testutil.TypicalSalesRecordEntries.BSBM;
import static seedu.address.testutil.TypicalSalesRecordEntries.BSPBT;
import static seedu.address.testutil.TypicalSalesRecordEntries.BSPGT;
import static seedu.address.testutil.TypicalSalesRecordEntries.getTypicalSalesBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySalesBook;
import seedu.address.model.SalesBook;


public class JsonSalesBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSalesBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSalesBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSalesBook(null));
    }

    private java.util.Optional<ReadOnlySalesBook> readSalesBook(String filePath) throws Exception {
        return new JsonSalesBookStorage(Paths.get(filePath)).readSalesBook(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSalesBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSalesBook(
                "notJsonFormatSalesBook.json"));
    }

    @Test
    public void readAndSaveSalesBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSalesBook.json");
        SalesBook original = getTypicalSalesBook();
        JsonSalesBookStorage jsonSalesBookStorage = new JsonSalesBookStorage(filePath);

        // Save in new file and read back
        jsonSalesBookStorage.saveSalesBook(original, filePath);
        ReadOnlySalesBook readBack = jsonSalesBookStorage.readSalesBook(filePath).get();
        assertEquals(original, new SalesBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.setSalesRecordEntry(BSBGT);
        jsonSalesBookStorage.saveSalesBook(original, filePath);
        readBack = jsonSalesBookStorage.readSalesBook(filePath).get();
        assertEquals(original, new SalesBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.setSalesRecordEntry(BSBBT);
        jsonSalesBookStorage.saveSalesBook(original, filePath);
        readBack = jsonSalesBookStorage.readSalesBook(filePath).get();
        assertEquals(original, new SalesBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.setSalesRecordEntry(BSPBT);
        jsonSalesBookStorage.saveSalesBook(original, filePath);
        readBack = jsonSalesBookStorage.readSalesBook(filePath).get();
        assertEquals(original, new SalesBook(readBack));

        // Save and read without specifying file path
        original.setSalesRecordEntry(BSPGT);
        jsonSalesBookStorage.saveSalesBook(original); // file path not specified
        readBack = jsonSalesBookStorage.readSalesBook().get(); // file path not specified
        assertEquals(original, new SalesBook(readBack));

        // Save and read without specifying file path
        original.setSalesRecordEntry(BSBBT);
        jsonSalesBookStorage.saveSalesBook(original); // file path not specified
        readBack = jsonSalesBookStorage.readSalesBook().get(); // file path not specified
        assertEquals(original, new SalesBook(readBack));

        // Save and read without specifying file path
        original.setSalesRecordEntry(BSBM);
        jsonSalesBookStorage.saveSalesBook(original); // file path not specified
        readBack = jsonSalesBookStorage.readSalesBook().get(); // file path not specified
        assertEquals(original, new SalesBook(readBack));
    }


    @Test
    public void saveSalesBook_nullSalesBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSalesBook(null, "SomeFile.json"));
    }

    private void saveSalesBook(ReadOnlySalesBook salesBook, String filePath) {
        try {
            new JsonSalesBookStorage(Paths.get(filePath))
                    .saveSalesBook(salesBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSalesBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSalesBook(new SalesBook(), null));
    }

}
