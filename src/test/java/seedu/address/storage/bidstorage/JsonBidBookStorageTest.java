package seedu.address.storage.bidstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bids.TypicalBid.BID_C;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;

public class JsonBidBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBidBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBidBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBidBook(null));
    }

    private java.util.Optional<ReadOnlyBidBook> readBidBook(String filePath) throws Exception {
        return new JsonBidBookStorage(Paths.get(filePath)).readBidBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBidBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBidBook("notJsonFormatBidBook.json"));
    }

    @Test
    public void readBidBook_invalidBidBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBidBook("invalidBidBook.json"));
    }

    @Test
    public void readBidBook_invalidAndValidBidBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBidBook("invalidAndValidBidBook.json"));
    }

    @Test
    public void readAndSaveBidBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBidBook.json");
        BidBook original = getTypicalBidBook();
        JsonBidBookStorage jsonBidBookStorage = new JsonBidBookStorage(filePath);

        // Save in new file and read back
        jsonBidBookStorage.saveBidBook(original, filePath);
        ReadOnlyBidBook readBack = jsonBidBookStorage.readBidBook(filePath).get();
        assertEquals(original, new BidBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBid(BID_C);
        original.removeBid(BID_C);
        jsonBidBookStorage.saveBidBook(original, filePath);
        readBack = jsonBidBookStorage.readBidBook(filePath).get();
        assertEquals(original, new BidBook(readBack));

        // Save and read without specifying file path
        original.addBid(BID_C);
        jsonBidBookStorage.saveBidBook(original); // file path not specified
        readBack = jsonBidBookStorage.readBidBook().get(); // file path not specified
        assertEquals(original, new BidBook(readBack));

    }

    @Test
    public void saveBidBook_nullBidBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBidBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code BidBook} at the specified {@code filePath}.
     */
    private void saveBidBook(ReadOnlyBidBook bidBook, String filePath) {
        try {
            new JsonBidBookStorage(Paths.get(filePath))
                    .saveBidBook(bidBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBidBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBidBook(new BidBook(), null));
    }
}

