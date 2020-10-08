package seedu.address.model.meeting;

import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Meeting {
    // Identity fields
    private final MeetingName name;
    private final Date date;
    private final Time time;
    private Set<Person> participants;

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingName name, Date date, Time time) {
        requireAllNonNull(name, date, time);
        this.name = name;
        this.date = date;
        this.time = time;

        this.participants = new HashSet<>();

        HashSet<Person> persons = new HashSet<>(Arrays.asList(SampleDataUtil.getSamplePersons()));
        participants.addAll(persons);
    }

    public MeetingName getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Set<Person> getParticipants() {
        return participants;
    }

    /**
     * Returns true if both meetings have the same name, date and time.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getName().equals(getName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime());
        return builder.toString();
    }
}
