package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MeetingBook implements ReadOnlyMeetingBook {

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
    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

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
    //// property-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the meeting book.
     */
    public boolean hasMeetings(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the meeting book.
     * The meeting must not already exist in the meeting book.
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing
     * meeting in the meeting book.
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

    /**
     * Removes meeting according to their bidder id.
     *
     */
    public void removeAllMeetingsWithBidderId(BidderId bidderId) {
        meetings.removeAllWithBidderId(bidderId);
    }

    /**
     * Removes all meetings with the specified propertyId.
     *
     * @param propertyId The propertyId of the property to be deleted.
     */
    public void removeAllMeetingsWithPropertyId(PropertyId propertyId) {
        meetings.removeAllWithPropertyId(propertyId);
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
