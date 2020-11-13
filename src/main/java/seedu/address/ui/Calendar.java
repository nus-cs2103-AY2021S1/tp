package seedu.address.ui;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.event.TimeEvent;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.util.TimeEventComparator;

public class Calendar {
    private static TimeEventComparator timeEventComparator = new TimeEventComparator();
    private ObservableList<Meeting> meetings;
    private ObservableList<Deliverable> deliverables;
    private ObservableList<TimeEvent> calendarList;

    /**
     * Creates a Calendar using the Deliverables and Meetings in the {@code deliverables}
     * and {@code meetings} respectively
     */
    public Calendar(ObservableList<Deliverable> deliverables, ObservableList<Meeting> meetings) {
        this.deliverables = deliverables;
        this.meetings = meetings;
        this.calendarList = FXCollections.observableArrayList();
        updateCalendarList();
    }

    /**
     * Updates the calendar list for every Meeting or Deliverable command execution.
     */
    public void updateCalendarList() {
        this.calendarList.clear();
        this.calendarList.addAll(meetings);
        this.calendarList.addAll(deliverables);
        Collections.sort(calendarList, timeEventComparator);
    }

    public ObservableList<TimeEvent> getTimeEvents() {
        return this.calendarList;
    }
}
