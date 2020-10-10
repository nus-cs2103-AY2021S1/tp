package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.calendar.CalendarMeeting;
import seedu.address.model.calendar.UniqueMeetingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MeetingBook implements ReadOnlyMeetingManager {

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

    /**
     * Creates a Meeting Book using the Meetings in the {@code toBeCopied}
     */
    public MeetingBook(ReadOnlyMeetingManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<CalendarMeeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code MeetingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyMeetingManager newData) {
        requireNonNull(newData);

        setMeetings(newData.getMeetingList());
    }
    //// property-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the meeting book.
     */
    public boolean hasMeetings(CalendarMeeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the meeting book.
     * The meeting must not already exist in the meeting book.
     */
    public void addMeeting(CalendarMeeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing
     * meeting in the meeting book.
     */
    public void setMeeting(CalendarMeeting target, CalendarMeeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code MeetingBook}.
     * {@code key} must exist in the meeting book.
     */
    public void removeMeeting(CalendarMeeting key) {
        meetings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings";
        // TODO: refine later
    }

    @Override
    public ObservableList<CalendarMeeting> getMeetingList() {
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
