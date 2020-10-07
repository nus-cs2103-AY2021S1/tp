package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MeetingBook implements ReadOnlyMeetingBook{

    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        meetings = new UniqueMeetingList();
    }

    public MeetingBook() {}

    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    public void resetData(ReadOnlyMeetingBook newData) {
        requireNonNull(newData);
        setMeetings(newData.getMeetingList());
    }

    //// meeting-level operations
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings";
        // TODO: refine later
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingBook // instanceof handles nulls
                && meetings.equals(((MeetingBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }
}
