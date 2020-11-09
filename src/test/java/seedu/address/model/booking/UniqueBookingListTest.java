package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_DAN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.ACTIVE_BOOKING_DAN;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;
import static seedu.address.testutil.TypicalBookings.CONFLICT_AMY_BOOKING_CHLOE;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.testutil.BookingBuilder;


public class UniqueBookingListTest {
    private final UniqueBookingList uniqueBookingList = new UniqueBookingList();

    @Test
    public void contains_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.contains(null));
    }

    @Test
    public void contains_bookingNotInList_returnsFalse() {
        assertFalse(uniqueBookingList.contains(BOOKING_BOB));
    }

    @Test
    public void contains_bookingInList_returnsTrue() {
        uniqueBookingList.add(BOOKING_BOB);
        assertTrue(uniqueBookingList.contains(BOOKING_BOB));
    }

    @Test
    public void add_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void add_duplicateBooking_throwsDuplicateBookingException() {
        uniqueBookingList.add(BOOKING_BOB);
        uniqueBookingList.add(BOOKING_BOB);

        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_BOB);
        expectedUniqueBookingList.add(BOOKING_BOB);

        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void add_duplicateActiveBooking_throwsDuplicateBookingException() {
        Booking activeAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        uniqueBookingList.add(activeAmy);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.add(activeAmy));
    }

    @Test
    public void add_conflictingBooking_throwsConflictingBookingException() {
        Booking activeAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        uniqueBookingList.add(activeAmy);
        Booking activeChloe = new BookingBuilder(CONFLICT_AMY_BOOKING_CHLOE).withIsActive(true).build();
        assertThrows(ConflictingBookingException.class, () -> uniqueBookingList.add(activeChloe));
    }

    @Test
    public void setBooking_nullTargetBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(null, BOOKING_AMY));
    }

    @Test
    public void setBooking_nullEditedBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(BOOKING_AMY, null));
    }

    @Test
    public void setBooking_targetBookingNotInList_throwsBookingNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(BOOKING_AMY, BOOKING_AMY));
    }

    @Test
    public void setBooking_editedBookingIsSameBooking_success() {
        uniqueBookingList.add(BOOKING_AMY);
        uniqueBookingList.setBooking(BOOKING_AMY, BOOKING_AMY);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_AMY);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBooking_editedBookingIsDifferentBooking_success() {
        uniqueBookingList.add(BOOKING_AMY);
        Booking editedBookingAmy = new BookingBuilder(BOOKING_AMY).withRoomId(VALID_ROOM_ID_BOB).build();
        uniqueBookingList.setBooking(BOOKING_AMY, editedBookingAmy);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(editedBookingAmy);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBooking_editedBookingsSameBookingBothActive_throwsDuplicateBookingException() {
        Booking activeAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        Booking activeBob = new BookingBuilder(BOOKING_BOB).withIsActive(true).build();

        uniqueBookingList.add(activeAmy);
        uniqueBookingList.add(activeBob);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBooking(activeAmy, activeBob));
    }

    @Test
    public void setBooking_editedBookingConflictWithBooking_throwsConflictBookingException() {
        Booking activeAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        uniqueBookingList.add(activeAmy);

        uniqueBookingList.add(BOOKING_BOB);

        Booking activeChloe = new BookingBuilder(CONFLICT_AMY_BOOKING_CHLOE).withIsActive(true).build();
        assertThrows(ConflictingBookingException.class, () -> uniqueBookingList.setBooking(
                BOOKING_BOB, activeChloe));
    }

    @Test
    public void getBooking_roomIdExistAndRoomActive() {
        uniqueBookingList.add(BOOKING_AMY);
        uniqueBookingList.add(ACTIVE_BOOKING_DAN);
        Booking expectedBooking = uniqueBookingList.getBooking(VALID_ROOM_ID_DAN);
        assertEquals(expectedBooking, ACTIVE_BOOKING_DAN);
    }

    @Test
    public void getBooking_roomIdDoNotExist_throwsBookingNotFoundException() {
        uniqueBookingList.add(BOOKING_BOB);
        uniqueBookingList.add(BOOKING_AMY);
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.getBooking(INVALID_ROOM_ID));
    }

    // no test for setBookingInactive yet, set false anyway

    @Test
    public void setBookings_nullUniqueBookingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBookings((UniqueBookingList) null));
    }

    @Test
    public void setBookings_uniqueBookingList_replacesOwnListWithProvidedUniqueBookingList() {
        uniqueBookingList.add(BOOKING_BOB);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_AMY);
        uniqueBookingList.setBookings(expectedUniqueBookingList);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBookings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBookings((List<Booking>) null));
    }

    @Test
    public void setBookings_list_replacesOwnListWithProvidedList() {
        uniqueBookingList.add(BOOKING_AMY);
        List<Booking> bookingList = Collections.singletonList(BOOKING_BOB);
        uniqueBookingList.setBookings(bookingList);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_BOB);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBookings_listWithDuplicateBookings_throwsDuplicateBookingException() {
        List<Booking> listWithDuplicateBookings = Arrays.asList(BOOKING_AMY, BOOKING_AMY);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBookings(listWithDuplicateBookings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getUnavailableRooms_success() {
        LocalDate sd = LocalDate.of(2020, 12, 9);
        LocalDate ed = LocalDate.of(2020, 12, 14);
        uniqueBookingList.add(new BookingBuilder(BOOKING_AMY).withIsActive(true).build());
        uniqueBookingList.add(new BookingBuilder(BOOKING_BOB).withIsActive(true).build());
        ObservableList<Integer> expectedOutput = FXCollections.observableArrayList(2104);

        ObservableList<Integer> actualOutput = uniqueBookingList.getUnavailableRooms(sd, ed);

        assertEquals(expectedOutput, actualOutput);
    }

}
