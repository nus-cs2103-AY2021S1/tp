package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.model.event.TimeEvent;

/**
 * Represents a Comparator for sorting TimeEvents chronologically.
 */
public class TimeEventComparator implements Comparator<TimeEvent> {
    /**
     * Compares TimeEvents in chronological order based on their indicator time.
     */
    public int compare(TimeEvent a, TimeEvent b) {
        LocalDateTime timeA = a.getIndicatorTime();
        LocalDateTime timeB = b.getIndicatorTime();

        return timeA.compareTo(timeB);
    }
}
