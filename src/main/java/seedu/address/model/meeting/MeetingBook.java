package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.UniqueMeetingList;

/**
 * Wraps all data at the meeting-book level
 * Duplicates are not allowed (by .isSameMeeting comparison)
 */
public class MeetingBook implements ReadOnlyMeetingBook {
    private final UniqueMeetingList meetings;

    {
        meetings = new UniqueMeetingList();
    }

    public MeetingBook() {}

    /**
     * Creates an MeetingBook using the Meetings in the {@code toBeCopied}
     */
    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();
        resetData(toBeCopied);
        sortMeetings();
    }

    /**
     * Sorts the contents of the meeting list by From in chronological order.
     */
    public void sortMeetings() {
        this.meetings.sortList();
    }

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code MeetingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyMeetingBook newData) {
        requireNonNull(newData);

        setMeetings(newData.getMeetingList());
    }

    /**
     * Adds a meeting to the meeting book.
     * The meeting must not already exist in the meeting book.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the meeting book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same as
     * another existing meeting in the meeting book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);
        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code MeetingBook}.
     * {@code key} must exist in the meeting book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings";
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingBook
                && meetings.equals(((MeetingBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }

    public ObservableList<Meeting> getInternalMeetingList() {
        return meetings.getInternalMeetingList();
    }
}
