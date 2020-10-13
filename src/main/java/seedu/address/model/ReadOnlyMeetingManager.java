package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.calendar.CalendarMeeting;

/**
 * Unmodifiable view of a calendar of meeting
 */
public interface ReadOnlyMeetingManager {

    /**
     * Returns an unmodifiable view of the meeting list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<CalendarMeeting> getMeetingList();
}
