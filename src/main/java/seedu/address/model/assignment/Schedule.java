package seedu.address.model.assignment;

import seedu.address.model.task.Deadline;

public class Schedule {
    private final boolean schedule;
    private final Deadline suggestedStartTime;
    private final Deadline suggestedEndTime;

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
    public Schedule(Deadline suggestedStartTime, Deadline suggestedEndTime) {
        schedule = true;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
    }

    public boolean isScheduled() {
        return schedule;
    }

    public Deadline getSuggestedStartTime() {
        return suggestedStartTime;
    }

    public Deadline getSuggestedEndTime() {
        return suggestedEndTime;
    }
}
