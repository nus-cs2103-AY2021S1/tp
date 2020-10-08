package seedu.address.model.meeting;

import seedu.address.model.person.Person;

import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Meeting {
    // Identity fields
    private final MeetingName name;
    private final Date date;
    private final Time time;

    public Meeting(MeetingName name, Date date, Time time) {
        requireAllNonNull(name, date, time);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public MeetingName getName(){
        return this.name;
    }

    public Date getDate(){
        return this.date;
    }

    public Time getTime(){
        return this.time;
    }

    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

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
