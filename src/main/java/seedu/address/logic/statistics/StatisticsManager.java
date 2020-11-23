package seedu.address.logic.statistics;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ModelManager;


public class StatisticsManager {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Statistics statistics;

    //  if statistics file exists

    /**
     * @param statistics
     */
    public StatisticsManager(Statistics statistics) {
        this.statistics = statistics;
        statistics.registerAppLogin();
    }


    /**
     * if first time opening the app
     */
    public StatisticsManager() {
        this.statistics = new Statistics();
        this.statistics.registerAppLogin();
    }

    /**
     * Registers the app logout process and logs the statistics information accordingly
     */
    public void doCleanup() {
        statistics.registerAppLogout();
        logger.info("Cleaning up: " + statistics.toString());
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public String getLastLoginString() {
        LocalDateTime lastLogin = getLastLogin();
        logger.info("Starting Up: " + statistics.toString());
        return lastLogin == null ? "None - first login." : lastLogin.toString();
    }

    public LocalDateTime getLastLogin() {
        List<Event> eventLog = statistics.getEventLog();

        int n = eventLog.size();
        if (n == 0 || n == 1) {
            return null;
        }

        boolean seenFirstLogin = false;
        int i;
        Event cur;
        // get the second most recent login event
        for (i = n - 1; i >= 0; --i) {
            cur = eventLog.get(i);

            if (cur.getEventType() != EventType.LOGIN) {
                continue;
            }

            if (!seenFirstLogin) {
                seenFirstLogin = true;
            } else {
                return cur.getLocalDateTime();
            }
        }

        return null;
    }
}
