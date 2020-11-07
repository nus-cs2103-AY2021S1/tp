package seedu.address.model.assignment;

<<<<<<< HEAD
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.task.Deadline;
=======
import seedu.address.model.task.Time;
>>>>>>> 6bec7fb237fbbcccd3fab8fc49fd036c908fcd59

public class Schedule {
    // message constraints occurs if saved data is modified wrongly
    public static final String MESSAGE_CONSTRAINTS = "Suggested start time should be"
            + "before suggested end time";
    public static final String START_TIME_MESSAGE_CONSTRAINS = "Suggested start time "
            + "should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String END_TIME_MESSAGE_CONSTRAINS = "Suggested end time "
            + "should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String NOT_SCHEDULED_CONSTRAINS = "Assignment is not scheduled but "
            + "suggest start time or end time is present";
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
<<<<<<< HEAD
    public Schedule(Deadline suggestedStartTime, Deadline suggestedEndTime) {
        checkArgument(isValidSchedule(suggestedStartTime, suggestedEndTime), MESSAGE_CONSTRAINTS);
=======
    public Schedule(Time suggestedStartTime, Time suggestedEndTime) {
>>>>>>> 6bec7fb237fbbcccd3fab8fc49fd036c908fcd59
        schedule = true;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
    }

    public boolean isValidSchedule(Deadline suggestedStartTime, Deadline suggestedEndTime) {
        return suggestedStartTime.isBefore(suggestedEndTime);
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
