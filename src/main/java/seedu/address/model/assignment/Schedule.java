package seedu.address.model.assignment;

public class Schedule {
    private final boolean schedule;
    private final Deadline suggestedStartTime;
    private final Deadline suggestedEndTime;

    /**
     * Constructor
     * Returns a false schedule
     */
    public Schedule() {
        schedule = false;
        suggestedStartTime = null;
        suggestedEndTime = null;
    }

    /**
     * Constructor
     * Returns a suggested schedule
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
