package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BookingBook;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.booking.Booking;



/**
 * An Immutable BookingBook that is serializable to JSON format.
 */
@JsonRootName(value = "bookingBook")
class JsonSerializableBookingBook {

    public static final String MESSAGE_DUPLICATE_BOOKING = "Bookings list contains duplicate active booking(s).";

    private final List<JsonAdaptedBooking> bookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBookingBook} with the given bookings.
     */
    @JsonCreator
    public JsonSerializableBookingBook(@JsonProperty("bookings") List<JsonAdaptedBooking> bookings) {
        this.bookings.addAll(bookings);
    }

    /**
     * Converts a given {@code ReadOnlyBookingBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBookingBook}.
     */
    public JsonSerializableBookingBook(ReadOnlyBookingBook source) {
        bookings.addAll(source.getBookingList().stream().map(JsonAdaptedBooking::new).collect(Collectors.toList()));
    }

    /**
     * Converts this booking book into the model's {@code BookingBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BookingBook toModelType() throws IllegalValueException {
        BookingBook bookingBook = new BookingBook();
        for (JsonAdaptedBooking jsonAdaptedBooking : bookings) {
            Booking booking = jsonAdaptedBooking.toModelType();
            if (bookingBook.containsActiveDuplicate(booking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOKING);
            }
            bookingBook.addBooking(booking);
        }
        return bookingBook;
    }

}
