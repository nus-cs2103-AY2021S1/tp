package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commons.SpecialName;
import seedu.address.model.person.Person;

public class Meeting {
    // Identity fields
    private final MeetingName meetingName;
    private final Date date;
    private final Time time;
    private final Set<Person> members = new HashSet<>();
    private final Set<SpecialName> agendas = new HashSet<>();
    private final Set<SpecialName> notes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingName name, Date date, Time time, Set<Person> members, Set<SpecialName> agendas,
                   Set<SpecialName> notes) {
        requireAllNonNull(name, date, time);
        this.meetingName = name;
        this.date = date;
        this.time = time;
        this.members.addAll(members);
        this.agendas.addAll(agendas);
        this.notes.addAll(notes);
    }

    public MeetingName getMeetingName() {
        return this.meetingName;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Set<Person> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public Set<SpecialName> getAgendas() {
        return Collections.unmodifiableSet(agendas);
    }

    public Set<SpecialName> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    /**
     * Returns true if both meetings have the same name, date and time.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getMeetingName().equals(getMeetingName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    public boolean isSameMeetingName(MeetingName otherMeetingName) {
        return meetingName.equals(otherMeetingName);
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
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
        return otherMeeting.getMeetingName().equals(getMeetingName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getMembers().equals(getMembers());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingName, date, time, members);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getMeetingName())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime())
                .append(" Members: ");
        getMembers().forEach(member -> builder.append(member.getName() + ", "));
        return builder.substring(0, builder.length() - 2);
    }
}
