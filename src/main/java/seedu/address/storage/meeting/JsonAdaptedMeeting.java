package seedu.address.storage.meeting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Admin;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Paperwork;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;
import seedu.address.model.meeting.Viewing;
import seedu.address.model.person.Phone;



/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String propertyId;
    private final String bidderId;
    private final String date;
    private final String venue;
    private final String typeOfMeeting;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("propertyId") String propertyId, @JsonProperty("bidderId") String bidderId,
                              @JsonProperty("date") String date, @JsonProperty("venue") String venue,
                              @JsonProperty("typeOfMeeting") String typeOfMeeting,
                              @JsonProperty("startTime") String startTime,
                              @JsonProperty("endTime") String endTime) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.date = date;
        this.venue = venue;
        this.typeOfMeeting = typeOfMeeting;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        propertyId = source.getPropertyId().toString();
        bidderId = source.getBidderId().toString();
        date = source.getDate().date;
        venue = source.getVenue().venue;
        typeOfMeeting = source.checkMeetingType();
        startTime = source.getStartTime().startTime;
        endTime = source.getEndTime().endTime;
    }


    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {

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

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartTime.class.getSimpleName()));
        }
        if (!StartTime.isValidStartTime(startTime)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final StartTime modelStartTime = new StartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidEndTime(endTime)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final EndTime modelEndTime = new EndTime(endTime);

        if (typeOfMeeting.contains("Paperwork")) {
            return new Paperwork(modelBidderId, modelPropertyId, modelDate, modelVenue, modelStartTime, modelEndTime);
        } else if (typeOfMeeting.contains("Viewing")) {
            return new Viewing(modelBidderId, modelPropertyId, modelDate, modelVenue, modelStartTime, modelEndTime);
        } else if (typeOfMeeting.contains("Admin")) {
            return new Admin(modelBidderId, modelPropertyId, modelDate, modelVenue, modelStartTime, modelEndTime);
        } else {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Meeting.class.getSimpleName()));
        }
    }
}
