package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final JsonAdaptedModule module;
    private final String meetingName;
    private final String date;
    private final String time;
    private final List<JsonAdaptedPerson> memberList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("module") JsonAdaptedModule module,
                              @JsonProperty("meeting name") String meetingName,
                              @JsonProperty("date") String date,
                              @JsonProperty("time") String time,
                              @JsonProperty("members") List<JsonAdaptedPerson> memberList) {
        this.module = module;
        this.meetingName = meetingName;
        this.date = date;
        this.time = time;
        if (memberList != null) {
            this.memberList.addAll(memberList);
        }
    }

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    public JsonAdaptedMeeting(Meeting source) {
        module = new JsonAdaptedModule(source.getModule());
        meetingName = source.getMeetingName().meetingName;
        date = source.getDate().value;
        time = source.getTime().value;
        memberList.addAll(source.getParticipants().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<Person> members = new ArrayList<>();
        for (JsonAdaptedPerson person : memberList) {
            members.add(person.toModelType());
        }

        Module modelModule = module.toModelType();

        if (meetingName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidMeetingName(meetingName)) {
            throw new IllegalValueException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        final MeetingName modelName = new MeetingName(meetingName);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(time);

        final Set<Person> modelMembers = new HashSet<>(members);

        return new Meeting(modelModule, modelName, modelDate, modelTime, modelMembers);
    }

}
