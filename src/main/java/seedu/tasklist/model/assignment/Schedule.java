package seedu.tasklist.model.assignment;

import seedu.tasklist.model.task.Time;

public class Schedule {
    private final boolean schedule;
    private final Time suggestedStartTime;
    private final Time suggestedEndTime;

    /**
     * Constructs a false schedule
     */
    public Schedule() {
        schedule = false;
        suggestedStartTime = null;
        suggestedEndTime = null;
    }

    /**
     * Constructs a suggested schedule
     */
    public Schedule(Time suggestedStartTime, Time suggestedEndTime) {
        schedule = true;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
    }

    public boolean isScheduled() {
        return schedule;
    }

    public Time getSuggestedStartTime() {
        return suggestedStartTime;
    }

    public Time getSuggestedEndTime() {
        return suggestedEndTime;
    }
}
