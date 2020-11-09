package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BookingBook;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.TypicalBookings;

public class JsonSerializableBookingBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBookingBookTest");
    private static final Path TYPICAL_BOOKINGS_FILE = TEST_DATA_FOLDER.resolve("typicalBookingsBookingBook.json");
    private static final Path INVALID_BOOKING_FILE = TEST_DATA_FOLDER.resolve("invalidBookingBookingBook.json");
    private static final Path DUPLICATE_BOOKING_FILE = TEST_DATA_FOLDER.resolve("duplicateBookingBookingBook.json");
    private static final Path DUPLICATE_INACTIVE_BOOKING_FILE =
            TEST_DATA_FOLDER.resolve("duplicateInactiveBookingBookingBook.json");

    @Test
    public void toModelType_typicalBookingFile_success() throws Exception {
        JsonSerializableBookingBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_BOOKINGS_FILE,
                JsonSerializableBookingBook.class).get();
        BookingBook bookingBookFromFile = dataFromFile.toModelType();
        BookingBook typicalBookingsBookingBook = TypicalBookings.getTypicalBookingBook();
        assertEquals(bookingBookFromFile, typicalBookingsBookingBook);
    }

    @Test
    public void toModelType_invalidBookingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBookingBook dataFromFile = JsonUtil.readJsonFile(INVALID_BOOKING_FILE,
                JsonSerializableBookingBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateaActiveBookings_throwsIllegalValueException() throws Exception {
        JsonSerializableBookingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BOOKING_FILE,
                JsonSerializableBookingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBookingBook.MESSAGE_DUPLICATE_BOOKING,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInactiveBookings_success() throws Exception {
        JsonSerializableBookingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INACTIVE_BOOKING_FILE,
                JsonSerializableBookingBook.class).get();
        BookingBook bookingBookFromFile = dataFromFile.toModelType();
        BookingBook expectedBookingBook = new BookingBook();
        System.out.print(bookingBookFromFile);
        System.out.print(expectedBookingBook);

        expectedBookingBook.addBooking(BOOKING_AMY);
        expectedBookingBook.addBooking(new BookingBuilder(BOOKING_AMY)
                .withId(12).build());

        assertEquals(bookingBookFromFile, expectedBookingBook);
    }
}
