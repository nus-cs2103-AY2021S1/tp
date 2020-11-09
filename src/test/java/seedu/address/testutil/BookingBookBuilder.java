package seedu.address.testutil;

import seedu.address.model.BookingBook;
import seedu.address.model.booking.Booking;

public class BookingBookBuilder {
    private BookingBook bookingBook;

    public BookingBookBuilder() {
        bookingBook = new BookingBook();
    }

    public BookingBookBuilder(BookingBook bookingBook) {
        this.bookingBook = bookingBook;
    }

    /**
     * Adds a new {@code Booking} to the {@code BookingBook} that we are building.
     */
    public BookingBookBuilder withBooking(Booking booking) {
        bookingBook.addBooking(booking);
        return this;
    }

    public BookingBook build() {
        return bookingBook;
    }
}
