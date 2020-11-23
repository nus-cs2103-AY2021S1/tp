package seedu.address.logic.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that captures the app usage statistics of a given user. These could range from time spent
 * on the app to individual quiz performance.
 */
public class Statistics {

    private int timesOpened;
    private List<Event> eventLog;

    /**
     * @param timesOpened
     * @param eventLog
     */
    public Statistics(int timesOpened, List<Event> eventLog) {
        this.timesOpened = timesOpened;
        this.eventLog = eventLog;
    }

    /**
     *
     */
    public Statistics() {
        timesOpened = 0;
        eventLog = new ArrayList<>();
    }

    /**
     *
     */
    public void registerAppLogin() {
        eventLog.add(new Event(EventType.LOGIN));
        timesOpened++;
    }

    /**
     *
     */
    public void registerAppLogout() {
        eventLog.add(new Event(EventType.LOGOUT));
    }

    public int getTimesOpened() {
        return timesOpened;
    }

    @Override
    public String toString() {
        return "Statistics{"
                + "timesOpened = " + timesOpened
                + ", eventLog = " + eventLog
                + '}';
    }

    public List<Event> getEventLog() {
        return eventLog;
    }
}
