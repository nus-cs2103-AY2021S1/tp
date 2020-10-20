package seedu.address.storage.calendar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calendar.CalendarAdmin;
import seedu.address.model.calendar.CalendarMeeting;
import seedu.address.model.calendar.CalendarPaperwork;
import seedu.address.model.calendar.CalendarTime;
import seedu.address.model.calendar.CalendarVenue;
import seedu.address.model.calendar.CalendarViewing;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.person.Phone;



/**
 * Jackson-friendly version of {@link CalendarMeeting}.
 */
public class JsonAdaptedMeeting {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String propertyId;
    private final String bidderId;
    private final String time;
    private final String venue;
    private final String typeOfMeeting;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("propertyId") String propertyId, @JsonProperty("bidderId") String bidderId,
                             @JsonProperty("time") String time, @JsonProperty("venue") String venue,
                              @JsonProperty("typeOfMeeting") String typeOfMeeting) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.time = time;
        this.venue = venue;
        this.typeOfMeeting = typeOfMeeting;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(CalendarMeeting source) {
        propertyId = source.getCalendarPropertyId().toString();
        bidderId = source.getCalendarBidderId().toString();
        time = source.getCalendarTime().time;
        venue = source.getCalendarVenue().venue;
        typeOfMeeting = source.checkMeetingType();
    }


    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public CalendarMeeting toModelType() throws IllegalValueException {

        if (propertyId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyId.class.getSimpleName()));
        }
        if (!PropertyId.isValidId(propertyId)) {
            throw new IllegalValueException(PropertyId.MESSAGE_CONSTRAINTS);
        }
        final PropertyId modelPropertyId = new PropertyId(propertyId);

        if (bidderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BidderId.class.getSimpleName()));
        }
        if (!BidderId.isValidId(bidderId)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final BidderId modelBidderId = new BidderId(bidderId);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalendarTime.class.getSimpleName()));
        }
        if (!CalendarTime.isValidCalendarTime(time)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final CalendarTime modelTime = new CalendarTime(time);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalendarVenue.class.getSimpleName()));
        }
        if (!CalendarVenue.isValidCalendarVenue(venue)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final CalendarVenue modelVenue = new CalendarVenue(venue);

        if (typeOfMeeting.contains("Paperwork")) {
            return new CalendarPaperwork(modelBidderId, modelPropertyId, modelTime, modelVenue);
        } else if (typeOfMeeting.contains("Viewing")) {
            return new CalendarViewing(modelBidderId, modelPropertyId, modelTime, modelVenue);
        } else if (typeOfMeeting.contains("Admin")) {
            return new CalendarAdmin(modelBidderId, modelPropertyId, modelTime, modelVenue);
        } else {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalendarMeeting.class.getSimpleName()));
        }
    }
}
