package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.task.Task;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private String name;
    private String description;
    private String publishDate;
    private String startDateTime;
    private String duration;
    private String note;
    private String isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("name") String name,
                              @JsonProperty("description") String description,
                              @JsonProperty("publishDate") String publishDate,
                              @JsonProperty("startDateTime") String startDateTime,
                              @JsonProperty("duration") String duration,
                              @JsonProperty("note") String note,
                              @JsonProperty("isDone") String isDone) {
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.isDone = isDone;
        this.note = note;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        name = source.getName();
        description = source.getDescription();
        publishDate = source.getPublishDate().toString();
        startDateTime = source.getStartDateTime().toString();
        if (source.getDuration() != null) {
            duration = source.getDuration().toString();
        } else {
            duration = null;
        }
        isDone = source.isDone().toString();
        note = source.getNote();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Meeting toModelType() throws IllegalValueException {

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Start date time"));
        }
        if (!Meeting.isValidStartDateTime(startDateTime)) {
            throw new IllegalValueException(Meeting.START_DATE_TIME_MESSAGE_CONSTRAINTS);
        }

        Meeting meeting = new Meeting(startDateTime);

        if (name != null) {
            meeting.setName(name);
        }
        if (!Meeting.isValidMeetingName(name)) {
            throw new IllegalValueException(Meeting.NAME_MESSAGE_CONSTRAINTS);
        }
        if (description != null) {
            meeting.setDescription(description);
        }
        if (!Meeting.isValidMeetingDescription(description)) {
            throw new IllegalValueException(Meeting.DESCRIPTION_MESSAGE_CONSTRAINTS);
        }
        if (publishDate == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Publish date"));
        }
        if (!Meeting.isValidPublishDate(publishDate)) {
            throw new IllegalValueException(Meeting.PUBLISH_DATE_MESSAGE_CONSTRAINTS);
        }
        if (duration != null) {
            meeting.setDuration(Time.valueOf(duration));
        }
        if (!Meeting.isValidDuration(duration)) {
            throw new IllegalValueException(Meeting.DURATION_MESSAGE_CONSTRAINTS);
        }
        if (isDone == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "Is done"));
        }
        if (!Meeting.isValidIsDone(isDone)) {
            throw new IllegalValueException(Meeting.IS_DONE_MESSAGE_CONSTRAINTS);
        }
        if (note != null) {
            meeting.setNote(note);
        }
        if (!Meeting.isValidNote(note)) {
            throw new IllegalValueException(Meeting.NOTE_MESSAGE_CONSTRAINTS);
        }

        meeting.setDone(Boolean.getBoolean(isDone));
        meeting.setPublishDate(LocalDate.parse(publishDate));

        return meeting;
    }

}
