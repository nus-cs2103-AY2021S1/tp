package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;


public class BookingBook implements ReadOnlyBookingBook {
    private final UniqueBookingList bookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {}

    /**
     * Creates an PersonBook using the Persons in the {@code toBeCopied}
     */
    public BookingBook(ReadOnlyBookingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the booking list with {@code bookings}.
     * {@code persons} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setBookings(bookings);
    }

    /**
     * Replaces the given booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the booking book.
     * The booking identity of {@code editedBooking} must not be the same as
     * another existing booking in the booking book.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        this.bookings.setBooking(target, editedBooking);
    }

    /**
     * Resets the existing data of this {@code BookingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBookingBook newData) {
        requireNonNull(newData);

        setBookings(newData.getBookingList());
    }

    /**
     * Adds a booking to the booking book.
     * The booking must not already exist in the booking book and conflict with existing booking.
     */
    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

    /**
     * Removes {@code booking} from this {@code BookingBook}.
     * {@code booking} must exist in the booking book.
     */
    public void removeBooking(Booking booking) {
        bookings.removeBooking(booking);
    }

    /**
     * Removes {@code booking} with matching {@code PersonId} from this {@code BookingBook}.
     */
    public void removeBookingWithPersonId(Integer personId) {
        bookings.removeBookingWithPersonId(personId);
    }


    /**
     * Get an active room.
     * @param roomID
     * @return An active room with matching room Id.
     */
    public Booking getBooking(int roomID) {
        return bookings.getBooking(roomID);
    }

    /**
     * Set the isActive state to false
     */
    public void setBookingInactive(int bookingId) {
        bookings.setBookingInactive(bookingId);
    }

    /**
     * Set the isActive state to true
     */
    public void setBookingActive(int bookingId) {
        bookings.setBookingActive(bookingId);
    }

    /**
     * Returns true if a booking with the same identity as {@code booking} exists in the booking book.
     */
    public boolean hasBooking(Booking booking) {
        return bookings.contains(booking);
    }

    /**
     * Returns true if a booking with the id is in the person book.
     */
    public boolean hasBookingWithId(Integer id) {
        requireNonNull(id);
        return bookings.hasBookingWithId(id);
    }
    /**
     * Returns true if booking itself is active and there exists a duplicate of it.
     */
    public boolean containsActiveDuplicate(Booking booking) {
        requireNonNull(booking);
        return bookings.containsActiveDuplicate(booking);
    }

    /**
     * Returns the booking with matching ID.
     */
    public Booking getBookingWithId(Integer id) {
        requireNonNull(id);
        return bookings.getBookingWithId(id);
    }

    /**
     * Returns a list of rooms that are available from the startDate to the endDate.
     */
    public ObservableList<Integer> getUnavailableRooms(LocalDate startDate, LocalDate endDate) {
        return bookings.getUnavailableRooms(startDate, endDate);
    }

    //// util methods

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingBook // instanceof handles nulls
                && bookings.equals(((BookingBook) other).bookings));
    }
}
