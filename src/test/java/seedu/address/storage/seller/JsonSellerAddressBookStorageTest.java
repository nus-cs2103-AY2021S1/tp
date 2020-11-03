package seedu.address.storage.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.seller.TypicalSeller.ALICE;
import static seedu.address.testutil.seller.TypicalSeller.HOON;
import static seedu.address.testutil.seller.TypicalSeller.IDA;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.storage.sellerstorage.JsonSellerAddressBookStorage;

public class JsonSellerAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSellerAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSellerAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                readSellerAddressBook(null));
    }

    private java.util.Optional<ReadOnlySellerAddressBook> readSellerAddressBook(String filePath) throws Exception {
        return new JsonSellerAddressBookStorage(Paths.get(filePath))
                .readSellerAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSellerAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readSellerAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidSellerAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readSellerAddressBook("invalidSellerAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidSellerAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readSellerAddressBook("invalidAndValidSellerAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        SellerAddressBook original = getTypicalSellerAddressBook();
        JsonSellerAddressBookStorage jsonSellerAddressBookStorage = new JsonSellerAddressBookStorage(filePath);

        // Save in new file and read back
        jsonSellerAddressBookStorage.saveSellerAddressBook(original, filePath);
        ReadOnlySellerAddressBook readBack = jsonSellerAddressBookStorage.readSellerAddressBook(filePath).get();
        assertEquals(original, new SellerAddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addSeller(HOON);
        original.removeSeller(ALICE);
        jsonSellerAddressBookStorage.saveSellerAddressBook(original, filePath);
        readBack = jsonSellerAddressBookStorage.readSellerAddressBook(filePath).get();
        assertEquals(original, new SellerAddressBook(readBack));

        // Save and read without specifying file path
        original.addSeller(IDA);
        jsonSellerAddressBookStorage.saveSellerAddressBook(original); // file path not specified
        readBack = jsonSellerAddressBookStorage.readSellerAddressBook().get(); // file path not specified
        assertEquals(original, new SellerAddressBook(readBack));

    }

    @Test
    public void saveSellerAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveSellerAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code sellerAddressBook} at the specified {@code filePath}.
     */
    private void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook, String filePath) {
        try {
            new JsonSellerAddressBookStorage(Paths.get(filePath))
                    .saveSellerAddressBook(sellerAddressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSellerAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveSellerAddressBook(new SellerAddressBook(), null));
    }
}
