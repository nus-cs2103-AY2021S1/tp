package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EXCEED_DURATION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_END_DATE;
import static seedu.address.storage.JsonAdaptedBooking.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;


public class JsonAdaptedBookingTest {
    private static final Integer INVALID_ID = 0;
    private static final Integer INVALID_ROOM_ID = 1000;
    private static final Integer INVALID_PERSON_ID = -1000;
    private static final LocalDate INVALID_START_DATE = LocalDate.of(2020, 10, 23);
    private static final LocalDate INVALID_END_DATE = LocalDate.of(2020, 10, 22);
    private static final LocalDate INVALID_END_DATE_EXCEED = LocalDate.of(2020, 12, 11);

    private static final Integer VALID_ID = BOOKING_BOB.getId();
    private static final Integer VALID_ROOM_ID = BOOKING_BOB.getRoomId();
    private static final Integer VALID_PERSON_ID = BOOKING_BOB.getPersonId();
    private static final LocalDate VALID_START_DATE = BOOKING_BOB.getStartDate();
    private static final LocalDate VALID_END_DATE = BOOKING_BOB.getEndDate();
    private static final boolean VALID_IS_ACTIVE = BOOKING_BOB.isActive();


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedBooking person = new JsonAdaptedBooking(BOOKING_BOB);
        assertEquals(BOOKING_BOB, person.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(INVALID_ID, VALID_ROOM_ID, INVALID_PERSON_ID, VALID_START_DATE,
                        VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_ID;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(
                null, VALID_ROOM_ID, VALID_PERSON_ID, VALID_START_DATE, VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "id");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRoomId_throwsIllegalValueException() {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(VALID_ID,
                INVALID_ROOM_ID, VALID_PERSON_ID, VALID_START_DATE, VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = Messages.MESSAGE_ROOM_ID_MISSING;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullRoomID_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(
                VALID_ID, null, VALID_PERSON_ID, VALID_START_DATE, VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "roomId");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPersonId_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_ID, VALID_ROOM_ID, INVALID_PERSON_ID, VALID_START_DATE,
                        VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_ID;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullPersonId_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(
                VALID_ID, VALID_ROOM_ID, null, VALID_START_DATE, VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "personId");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_endDateBeforeStartDate_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_ID, VALID_ROOM_ID, VALID_PERSON_ID, INVALID_START_DATE,
                        INVALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = MESSAGE_INVALID_START_END_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_ID, VALID_ROOM_ID, VALID_PERSON_ID, null,
                        VALID_END_DATE, VALID_IS_ACTIVE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "startDate");
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(
                VALID_ID, VALID_ROOM_ID, VALID_PERSON_ID, VALID_START_DATE, null, VALID_IS_ACTIVE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "endDate");
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_endDateExceed_throwsIllegalValueException() {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(
                VALID_ID, VALID_ROOM_ID, VALID_PERSON_ID, INVALID_START_DATE, INVALID_END_DATE_EXCEED, VALID_IS_ACTIVE);
        String expectedMessage = MESSAGE_EXCEED_DURATION;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }
}
