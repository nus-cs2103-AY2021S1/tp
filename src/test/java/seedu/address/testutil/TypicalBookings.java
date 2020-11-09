package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.CONFLICT_AMY_END_DATE_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.CONFLICT_AMY_ROOM_ID_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.CONFLICT_AMY_START_DATE_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.CONFLICT_AMY_VALID_BOOKING_ID_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_DAN;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.BookingBook;
import seedu.address.model.booking.Booking;


public class TypicalBookings {

    public static final LocalDate STARTDATE_1 = LocalDate.of(2020, 10, 20);
    public static final LocalDate ENDDATE_1 = LocalDate.of(2020, 10, 25);
    public static final LocalDate STARTDATE_2 = LocalDate.of(2020, 10, 23);
    public static final LocalDate ENDDATE_2 = LocalDate.of(2020, 10, 26);
    public static final LocalDate STARTDATE_3 = LocalDate.of(2020, 10, 26);
    public static final LocalDate ENDDATE_3 = LocalDate.of(2020, 10, 27);

    public static final LocalDate EDITED_ENDDATE_1 = LocalDate.of(2020, 10, 24);
    public static final LocalDate START_DATE_AFTER_END_DATE_1 = STARTDATE_1.plusMonths(1);
    public static final LocalDate EXCEED_1_MONTH_END_DATE_1 = STARTDATE_1.plusMonths(2);

    public static final Booking BOOKING_1 = new Booking(2103, 1, STARTDATE_1, ENDDATE_1,
            false, 1);
    public static final Booking BOOKING_2 = new Booking(2104, 2, STARTDATE_1, ENDDATE_1,
            false, 2);
    public static final Booking BOOKING_3 = new Booking(2105, 3, STARTDATE_2, ENDDATE_2,
            false, 3);
    public static final Booking BOOKING_4 = new Booking(2115, 4, STARTDATE_2, ENDDATE_2,
            false, 4);
    public static final Booking BOOKING_5 = new Booking(2104, 5, STARTDATE_3, ENDDATE_3,
            false, 5);
    public static final Booking BOOKING_6 = new Booking(2103, 1, STARTDATE_3, ENDDATE_3,
            false, 6);

    public static final Integer EDITED_ROOM_ID_1 = 2106;
    public static final Integer BOOKING_ID_1 = 1;
    public static final Integer INVALID_BOOKING_ID = 100;

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new Booking(VALID_ROOM_ID_AMY, VALID_PERSON_ID_AMY,
            VALID_START_DATE_AMY, VALID_END_DATE_AMY, false, VALID_BOOKING_ID_AMY);
    public static final Booking BOOKING_BOB = new Booking(VALID_ROOM_ID_BOB, VALID_PERSON_ID_BOB,
            VALID_START_DATE_BOB, VALID_END_DATE_BOB, false, VALID_BOOKING_ID_BOB);
    public static final Booking CONFLICT_AMY_BOOKING_CHLOE = new Booking(CONFLICT_AMY_ROOM_ID_CHLOE,
            VALID_PERSON_ID_CHLOE, CONFLICT_AMY_START_DATE_CHLOE, CONFLICT_AMY_END_DATE_CHLOE,
            false, CONFLICT_AMY_VALID_BOOKING_ID_CHLOE);
    public static final Booking ACTIVE_BOOKING_DAN = new Booking(VALID_ROOM_ID_DAN, VALID_PERSON_ID_DAN,
            VALID_START_DATE_DAN, VALID_END_DATE_DAN, true, VALID_BOOKING_ID_DAN);

    private TypicalBookings() {} // prevents instantiation

    /**
     * Returns an {@code PersonBook} with all the typical persons.
     */
    public static BookingBook getTypicalBookingBook() {
        BookingBook ab = new BookingBook();
        for (Booking r : getTypicalBookings()) {
            ab.addBooking(r);
        }
        return ab;
    }

    public static List<Booking> getTypicalBookings() {
        return new ArrayList<>(Arrays.asList(BOOKING_1, BOOKING_2, BOOKING_3, BOOKING_4, BOOKING_5, BOOKING_6));
    }

}
