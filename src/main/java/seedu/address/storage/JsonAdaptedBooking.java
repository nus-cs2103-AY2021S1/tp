package seedu.address.storage;

import static java.time.temporal.ChronoUnit.DAYS;
import static seedu.address.commons.core.Messages.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;



public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final Integer id;
    private final Integer roomId;
    private final Integer personId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final boolean isActive;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("id") Integer id,
                              @JsonProperty("roomId") Integer roomId,
                              @JsonProperty("personId") Integer personId,
                              @JsonProperty("startDate") LocalDate startDate,
                              @JsonProperty("endDate") LocalDate endDate,
                              @JsonProperty("isActive") Boolean isActive) {

        this.id = id;
        this.roomId = roomId;
        this.personId = personId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        id = source.getId();
        roomId = source.getRoomId();
        personId = source.getPersonId();
        startDate = source.getStartDate();
        endDate = source.getEndDate();
        isActive = source.isActive();
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        } else if (id <= 0) {
            throw new IllegalValueException(MESSAGE_INVALID_PERSON_ID);
        }
        final Integer modelId = id;

        if (roomId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "roomId"));
        } else if (roomId > 2132 || roomId < 2103) {
            throw new IllegalValueException(MESSAGE_ROOM_ID_MISSING);
        }
        final Integer modelRoomId = roomId;

        if (personId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "personId"));
        } else if (personId <= 0) {
            throw new IllegalValueException(MESSAGE_INVALID_PERSON_ID);
        }
        final Integer modelPersonId = personId;

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "startDate"));
        } else if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "endDate"));
        } else if (!startDate.isBefore(endDate)) {
            throw new IllegalValueException(MESSAGE_INVALID_START_END_DATE);
        }

        long noOfDaysBetween = DAYS.between(startDate, endDate);
        int duration = (int) noOfDaysBetween;

        if (duration > 30) {
            throw new IllegalValueException(MESSAGE_EXCEED_DURATION);
        }

        final LocalDate modelStartDate = startDate;
        final LocalDate modelEndDate = endDate;

        final boolean modelIsActive = isActive;

        return new Booking(modelRoomId, modelPersonId, modelStartDate, modelEndDate, modelIsActive, modelId);
    }

}
