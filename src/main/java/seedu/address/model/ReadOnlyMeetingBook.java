package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;

/**
 * Unmodifiable view of a calendar of meeting
 */
public interface ReadOnlyMeetingBook {

    /**
     * Returns an unmodifiable view of the meeting list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}
