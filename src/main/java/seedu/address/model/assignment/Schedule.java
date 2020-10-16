package seedu.address.model.assignment;

public class Schedule {
    private String dummyDeadline = "01-01-2000 0101";
    private boolean schedule;
    private Deadline suggestedStartTime;
    private Deadline suggestedEndTime;

    /**
     * Constructor
     * Returns a false schedule with dummy value
     */
    public Schedule() {
        schedule = false;
        suggestedStartTime = new Deadline(dummyDeadline);
        suggestedEndTime = new Deadline(dummyDeadline);
    }

    /**
     * Constructor
     * @param suggestedStartTime
     * @param suggestedEndTime
     */
    public Schedule(Deadline suggestedStartTime, Deadline suggestedEndTime) {
        schedule = !suggestedStartTime.value.equals(dummyDeadline);
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
