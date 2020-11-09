package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookingBuilder;


public class BookingTest {

    @Test
    public void hasConflict() {
        // both booking are inactive -> return false
        Booking conflictedAmy1 = new BookingBuilder(BOOKING_AMY)
                .withEndDate(LocalDate.of(2020, 10, 11)).build();
        assertFalse(BOOKING_AMY.hasConflict(conflictedAmy1));

        // both booking are active, booking do not overlap -> return false
        Booking activeAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();

        Booking activeNoConflictAmy = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 12))
                .withEndDate(LocalDate.of(2020, 10, 15)).build();
        assertFalse(activeAmy.hasConflict(activeNoConflictAmy));

        // both booking are active, other booking has same startDate, earlier endDate -> return true

        Booking activeConflictedAmy = new BookingBuilder(activeAmy)
                .withEndDate(LocalDate.of(2020, 10, 11)).build();

        assertTrue(activeAmy.hasConflict(activeConflictedAmy));

        // both booking are active, other booking has later startDate, same endDate -> return true
        activeConflictedAmy = new BookingBuilder(activeAmy)
                .withStartDate(LocalDate.of(2020, 10, 3)).build();
        assertTrue(activeConflictedAmy.hasConflict(activeAmy));

        // booking A starts on the day in which Booking B ends -> return false
        activeConflictedAmy = new BookingBuilder(activeAmy)
                .withStartDate(VALID_END_DATE_AMY)
                .withEndDate(LocalDate.of(2020, 10, 11)).build();
        assertFalse(conflictedAmy1.hasConflict(activeAmy));

        // booking B starts on the day in which Booking A ends
        assertFalse(activeAmy.hasConflict(activeConflictedAmy));
    }

    @Test
    public void hasOverlap() {
        // booking ends before startDate -> return False
        Booking overlapAmy1 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 12))
                .withEndDate(LocalDate.of(2020, 10, 16))
                .withIsActive(true)
                .build();
        assertFalse(overlapAmy1.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 11)));

        // Case 1: booking ends after startDate but before endDate
        Booking overlapAmy2 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 7))
                .withEndDate(LocalDate.of(2020, 10, 12))
                .withIsActive(true)
                .build();
        assertTrue(overlapAmy2.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 14)));

        // Case 2: booking starts after startDate and ends after endDate
        Booking overlapAmy3 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 11))
                .withEndDate(LocalDate.of(2020, 10, 16))
                .withIsActive(true)
                .build();
        assertTrue(overlapAmy3.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 14)));

        // Case 3: booking is within the startDate and endDate
        Booking overlapAmy4 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 10))
                .withEndDate(LocalDate.of(2020, 10, 13))
                .withIsActive(true)
                .build();
        assertTrue(overlapAmy4.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 14)));

        // Case 4: booking is on the startDate and endDate
        Booking overlapAmy5 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 9))
                .withEndDate(LocalDate.of(2020, 10, 14))
                .withIsActive(true)
                .build();
        assertTrue(overlapAmy5.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 14)));

        // booking starts after endDate
        Booking overlapAmy6 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(LocalDate.of(2020, 10, 16))
                .withEndDate(LocalDate.of(2020, 10, 20))
                .withIsActive(true)
                .build();
        assertFalse(overlapAmy6.hasOverlap(LocalDate.of(2020, 10, 9),
                LocalDate.of(2020, 10, 14)));
    }

    @Test
    public void getDuration() {
        // same value -> return true
        int duration = BOOKING_AMY.getDuration();
        assertEquals(duration, BOOKING_DURATION_AMY);

        // different value -> return false
        int duration1 = 6;
        assertNotEquals(duration1, BOOKING_DURATION_AMY);
    }

    @Test
    public void equals() {
        // same value -> return true
        Booking bookingAmyCopy = new BookingBuilder(BOOKING_AMY).build();
        assertTrue(bookingAmyCopy.equals(BOOKING_AMY));

        // same object -> return true
        assertTrue(BOOKING_AMY.equals(BOOKING_AMY));

        //null -> return false
        assertFalse(BOOKING_AMY.equals(null));

        // different type -> returns false
        assertFalse(BOOKING_AMY.equals(5));

        // different roomId -> return false
        Booking editedBookingAmy = new BookingBuilder(BOOKING_AMY).withRoomId(VALID_ROOM_ID_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different personId -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withPersonId(VALID_PERSON_ID_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different startDate -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withStartDate(VALID_START_DATE_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different endDate -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withEndDate(VALID_END_DATE_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different isActive -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        //different Booking Id -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withId(VALID_BOOKING_ID_BOB).build();
        assertFalse(BOOKING_AMY.equals(editedBookingAmy));
    }
}


