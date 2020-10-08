package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MeetingBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.meeting.Meeting;

public class JsonSerializableMeetingBook {

    public static final String MESSAGE_DUPLICATE_MEETING = "Meetings list contains duplicate meeting(s).";

    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    @JsonCreator
    public JsonSerializableMeetingBook(@JsonProperty("meetings") List<JsonAdaptedMeeting> meetings) {
        this.meetings.addAll(meetings);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableMeetingBook(ReadOnlyMeetingBook source) {
        meetings.addAll(source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
    }

    /**
     * Converts this meeting book into the model's {@code MeetingBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MeetingBook toModelType() throws IllegalValueException {
        MeetingBook meetingBook = new MeetingBook();
        for (JsonAdaptedMeeting jsonAdaptedMeeting : meetings) {
            Meeting meeting = jsonAdaptedMeeting.toModelType();
            if (meetingBook.hasMeeting(meeting)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
            }
            meetingBook.addMeeting(meeting);
        }
        return meetingBook;
    }

}
