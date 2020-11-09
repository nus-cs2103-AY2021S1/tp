package seedu.address.storage.bidder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.HOON;
import static seedu.address.testutil.bidder.TypicalBidder.IDA;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.storage.bidderstorage.JsonBidderAddressBookStorage;

public class JsonBidderAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonBidderAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBidderAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBidderAddressBook(null));
    }

    private java.util.Optional<ReadOnlyBidderAddressBook> readBidderAddressBook(String filePath) throws Exception {
        return new JsonBidderAddressBookStorage(Paths.get(filePath))
                .readBidderAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBidderAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readBidderAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidBidderAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readBidderAddressBook("invalidBidderAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidBidderAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readBidderAddressBook("invalidAndValidBidderAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        BidderAddressBook original = getTypicalBidderAddressBook();
        JsonBidderAddressBookStorage jsonBidderAddressBookStorage = new JsonBidderAddressBookStorage(filePath);

        // Save in new file and read back
        jsonBidderAddressBookStorage.saveBidderAddressBook(original, filePath);
        ReadOnlyBidderAddressBook readBack = jsonBidderAddressBookStorage.readBidderAddressBook(filePath).get();
        assertEquals(original, new BidderAddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBidder(HOON);
        original.removeBidder(ALICE);
        jsonBidderAddressBookStorage.saveBidderAddressBook(original, filePath);
        readBack = jsonBidderAddressBookStorage.readBidderAddressBook(filePath).get();
        assertEquals(original, new BidderAddressBook(readBack));

        // Save and read without specifying file path
        original.addBidder(IDA);
        jsonBidderAddressBookStorage.saveBidderAddressBook(original); // file path not specified
        readBack = jsonBidderAddressBookStorage.readBidderAddressBook().get(); // file path not specified
        assertEquals(original, new BidderAddressBook(readBack));

    }

    @Test
    public void saveBidderAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveBidderAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code bidderAddressBook} at the specified {@code filePath}.
     */
    private void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook, String filePath) {
        try {
            new JsonBidderAddressBookStorage(Paths.get(filePath))
                    .saveBidderAddressBook(bidderAddressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBidderAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBidderAddressBook(new BidderAddressBook(), null));
    }
}
