package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.model.booking.exception.ExceedDurationStayException;

public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Returns true if the list contains an equivalent active booking as the given argument.
     */
    public boolean containsActiveDuplicate(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream()
                .filter(booking -> booking.isActive() == true)
                .anyMatch(toCheck::isSameBooking);
    }

    /**
     * Returns true if the list contains a booking with the given id.
     */
    public boolean hasBookingWithId(Integer id) {
        requireNonNull(id);
        return internalList.stream().anyMatch(booking -> booking.getId().equals(id));
    }

    /**
     * Returns the booking with the given id.
     * The booking must already exist in the list.
     * Or BookingNotFoundException is thrown.
     */
    public Booking getBookingWithId(Integer id) {
        requireNonNull(id);

        if (!hasBookingWithId(id)) {
            throw new BookingNotFoundException();
        }

        return internalList.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst().get();
    }

    /**
     * Removes a booking from the List.
     * The booking must already exist in the list.
     * Or BookingNotFoundException is thrown.
     */
    public void removeBooking(Booking toRemove) {
        requireNonNull(toRemove);

        if (!contains(toRemove)) {
            throw new BookingNotFoundException();
        }

        internalList.remove(toRemove);
    }

    /**
     * Removes bookings associated with a personId from the List.
     */
    public void removeBookingWithPersonId(Integer personID) {
        requireNonNull(personID);

        setBookings(internalList.stream()
                .filter(booking -> !booking.getPersonId().equals(personID))
                .collect(Collectors.toList()));
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list if the booking is active
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        // check if exceed duration of 30-day stay
        if (toAdd.getDuration() > 30) {
            throw new ExceedDurationStayException();
        }
        // check active duplicate
        if (containsActiveDuplicate(toAdd)) {
            throw new DuplicateBookingException();
        }
        // check if conflict
        boolean hasConflict;

        hasConflict = internalList.stream().anyMatch(booking -> booking.hasConflict(toAdd));
        if (hasConflict) {
            throw new ConflictingBookingException();
        } else {
            internalList.add(toAdd);
        }
    }

    /**
     * Returns a booking with the room ID
     * @param roomId The room ID of the booking
     * @return The booking that contains the room ID
     */
    public Booking getBooking(int roomId) {
        requireNonNull(roomId);
        return internalList.stream()
                .filter(booking -> booking.getRoomId().equals(roomId))
                .filter(Booking::isActive)
                .findFirst()
                .orElseThrow(() -> new BookingNotFoundException());
    }

    /**
     * Sets a booking to inactive. Create new booking and set.
     * @param bookingId The booking id to be set inactive
     */
    public void setBookingInactive(int bookingId) {
        requireNonNull(bookingId);
        Booking booking = getBookingWithId(bookingId);
        Booking editedBooking = new Booking(booking.getRoomId(), booking.getPersonId(),
                booking.getStartDate(), booking.getEndDate(), false, booking.getId());
        setBooking(booking, editedBooking);
    }

    /**
     * Sets a booking to active. Create new booking and set.
     * @param bookingId The booking id to be set inactive
     */
    public void setBookingActive(int bookingId) {
        requireNonNull(bookingId);
        Booking booking = getBookingWithId(bookingId);
        Booking editedBooking = new Booking(booking.getRoomId(), booking.getPersonId(),
                booking.getStartDate(), booking.getEndDate(), true, booking.getId());
        setBooking(booking, editedBooking);
    }


    /**
     * Sets a target booking to edited booking.
     * @param target The booking to be replaced.
     * @param editedBooking The edited booking to be added to the unique booking list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);
        // Check if booking exists
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        // Check if duplicate booking
        if (!target.isSameBooking(editedBooking) && containsActiveDuplicate(editedBooking)) {
            System.out.println("containsActive");
            throw new DuplicateBookingException();
        }

        // Check conflict
        boolean hasConflict;

        hasConflict = internalList.stream()
                .filter(booking -> !booking.equals(target))
                .anyMatch(booking -> booking.hasConflict(editedBooking));
        if (hasConflict) {
            throw new ConflictingBookingException();
        }

        // Check if exceed duration
        if (editedBooking.getDuration() > 30) {
            throw new ExceedDurationStayException();
        }

        internalList.set(index, editedBooking);
    }

    /**
     * Replaces the bookings in the current list with bookings in replacement unique booking list.
     */
    public void setBookings(UniqueBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new DuplicateBookingException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Returns true if {@code bookings} contains only unique bookings.
     */
    private boolean bookingsAreUnique(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).equals(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a list of unavailable rooms that overlap with the period from start date to end date.
     */
    public ObservableList<Integer> getUnavailableRooms(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableArrayList(internalList.stream().filter(x -> x.hasOverlap(startDate, endDate))
                                    .map(Booking::getRoomId)
                                    .collect(Collectors.toList()));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
