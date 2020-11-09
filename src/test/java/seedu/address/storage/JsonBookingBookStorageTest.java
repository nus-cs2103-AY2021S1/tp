package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.BOOKING_1;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BookingBook;
import seedu.address.model.ReadOnlyBookingBook;

public class JsonBookingBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBookingBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBookingBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBookingBook(null));
    }

    private java.util.Optional<ReadOnlyBookingBook> readBookingBook(String filePath) throws Exception {
        return new JsonBookingBookStorage(Paths.get(filePath)).readBookingBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBookingBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBookingBook("notJsonFormatBookingBook.json"));
    }

    @Test
    public void readBookingBook_invalidBookingBookingBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookingBook("invalidBookingBookingBook.json"));
    }

    @Test
    public void readBookingBook_invalidAndValidBookingBookingBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readBookingBook("invalidAndValidBookingBookingBook.json"));
    }

    @Test
    public void readAndSaveBookingBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBookingBook.json");
        BookingBook original = getTypicalBookingBook();
        JsonBookingBookStorage jsonBookingBookStorage = new JsonBookingBookStorage(filePath);

        // Save in new file and read back
        jsonBookingBookStorage.saveBookingBook(original, filePath);
        ReadOnlyBookingBook readBack = jsonBookingBookStorage.readBookingBook(filePath).get();
        assertEquals(original, new BookingBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBooking(BOOKING_AMY);
        original.removeBooking(BOOKING_1);
        jsonBookingBookStorage.saveBookingBook(original, filePath);
        readBack = jsonBookingBookStorage.readBookingBook(filePath).get();
        assertEquals(original, new BookingBook(readBack));

        // Save and read without specifying file path
        original.addBooking(BOOKING_BOB);
        jsonBookingBookStorage.saveBookingBook(original); // file path not specified
        readBack = jsonBookingBookStorage.readBookingBook().get(); // file path not specified
        assertEquals(original, new BookingBook(readBack));

    }

    @Test
    public void saveBookingBook_nullBookingBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookingBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code personBook} at the specified {@code filePath}.
     */
    private void saveBookingBook(ReadOnlyBookingBook addressBook, String filePath) {
        try {
            new JsonBookingBookStorage(Paths.get(filePath))
                    .saveBookingBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBookingBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookingBook(new BookingBook(), null));
    }
}
