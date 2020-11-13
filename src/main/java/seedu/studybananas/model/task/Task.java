package seedu.studybananas.model.task;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

import seedu.studybananas.ui.scheduleui.TaskCell;

/**
 * Represents a Task in the StudyBananas.
 */
public class Task {

    private final Title title;
    private final Optional<Description> description;
    private final Optional<DateTime> dateTime;
    private final Optional<Duration> duration;

    // Not sure if this is a good practice. This is used by the ListChangeListener in TimeScale
    private TaskCell taskCellBind;

    /**
     * Initializes a Task.
     *
     * @param title       Title of the task.
     * @param description Description of the task.
     * @param dateTime    Date and Time of the task (Optional)
     */
    public Task(Title title, Description description, DateTime dateTime, Duration duration) {
        requireNonNull(title);
        this.title = title;
        this.description = Optional.ofNullable(description);
        this.dateTime = Optional.ofNullable(dateTime);
        this.duration = Optional.ofNullable(duration);
    }

    public Optional<Description> getDescription() {
        return description;
    }

    public Optional<DateTime> getDateTime() {
        return dateTime;
    }

    public Optional<Duration> getDuration() {
        return duration;
    }

    /**
     * Util function for {@Code TaskCell}, so duration must exist.
     * @return
     */
    public boolean isLongerThanAnHour() {
        assert duration.isPresent() : "You shouldn't call this method!!";
        return getNumberOfMinuteHappenToday() >= 60;
    }

    public Title getTitle() {
        return title;
    }

    private boolean hasDescription() {
        return description.isPresent();
    }

    private boolean hasDateTime() {
        return dateTime.isPresent();
    }

    private boolean hasDuration() {
        return duration.isPresent();
    }

    /**
     * Returns true if both tasks have the same title and description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = other;
        return otherTask.getTitle().rigorousEquals(this.getTitle())
                && (other.getDescription().equals(this.getDescription())
                || haveSameDescription(otherTask, this))
                && (other.getDateTime().equals(this.getDateTime())
                || haveSameDateTime(otherTask, this))
                && (other.getDuration().equals(this.getDuration())
                || haveSameDuration(otherTask, this));
    }

    private boolean bothHaveDescription(Task t1, Task t2) {
        return t1.hasDescription() && t2.hasDescription();
    }

    private boolean haveSameDescription(Task t1, Task t2) {
        return bothHaveDescription(t1, t2)
                && t1.getDescription().get().rigorousEquals(t2.getDescription().get());
    }

    private boolean bothHaveDateTime(Task t1, Task t2) {
        return t1.hasDateTime() && t2.hasDateTime();
    }

    private boolean haveSameDateTime(Task t1, Task t2) {
        return bothHaveDateTime(t1, t2)
                && t1.getDateTime().get().equals(t2.getDateTime().get());
    }

    private boolean bothHaveDuration(Task t1, Task t2) {
        return t1.hasDuration() && t2.hasDuration();
    }

    private boolean haveSameDuration(Task t1, Task t2) {
        return bothHaveDuration(t1, t2)
                && t1.getDuration().get().equals(t2.getDuration().get());
    }

    /**
     * Checks whether the duration of {@code otherTask} overlaps
     * with the duration of {@code this} task.
     *
     * @param otherTask Task to be checked.
     * @return True if the two tasks overlap, false other wise.
     */
    public boolean isDateTimeOverlapped(Task otherTask) {
        if (!bothHaveDateTime(otherTask, this)) {
            return false;
        } else {
            LocalDateTime thisTaskStartingTime = this.getTaskStartingTime();
            LocalDateTime thisTaskEndingTime = this.getTaskEndingTime();
            LocalDateTime otherTaskStartingTime = otherTask.getTaskStartingTime();
            LocalDateTime otherTaskEndingTime = otherTask.getTaskEndingTime();
            return thisTaskStartingTime.isBefore(otherTaskEndingTime)
                    && thisTaskEndingTime.isAfter(otherTaskStartingTime);
        }
    }

    private LocalDateTime getTaskStartingTime() {
        return getDateTime().get().dateTime;
    }

    private LocalDateTime getTaskEndingTime() {
        Integer duration = this.duration.map(dur -> dur.duration).orElse(0);
        LocalDateTime endingDateTime = getDateTime().get().dateTime.plus(duration, ChronoUnit.MINUTES);
        return endingDateTime;
    }

    private StringBuilder getTitleString() {
        StringBuilder titleInString = new StringBuilder();
        titleInString.append("Title: ").append(getTitle() + "\n");
        return titleInString;
    }

    private StringBuilder getDescriptionString() {
        StringBuilder emptyString = new StringBuilder("");
        return description.map(desc ->
                new StringBuilder("Description: ")
                        .append(desc.toString() + "\n")).orElse(emptyString);
    }

    private StringBuilder getDateTimeString() {
        StringBuilder emptyString = new StringBuilder("");
        return dateTime.map(time ->
                new StringBuilder("Time: ")
                        .append(time.toString() + "\n")).orElse(emptyString);
    }

    private StringBuilder getDurationString() {
        StringBuilder emptyString = new StringBuilder("");
        return duration.map(time ->
                new StringBuilder("Duration: ")
                        .append(time.toString() + "\n")).orElse(emptyString);
    }

    public TaskCell getTaskCellBind() {
        return taskCellBind;
    }

    public void setTaskCellBind(TaskCell taskCellBind) {
        this.taskCellBind = taskCellBind;
    }

    /**
     * Check if the duration of the tasks would happen today.
     *
     * @return True if it takes place today, false otherwise.
     */
    public boolean happensToday() {
        return duration.isPresent() && dateTime.isPresent()
                && (dateTime.get().isToday() || startFromOldAndExtendToToday(dateTime.get(), duration.get()));
    }

    private boolean startFromOldAndExtendToToday(DateTime dateTime, Duration duration) {
        LocalDateTime startTime = dateTime.dateTime;
        LocalDateTime endTime = dateTime.dateTime.plusMinutes(duration.duration);
        LocalDate today = LocalDate.now();
        return today.isAfter(startTime.toLocalDate()) && (!endTime.toLocalDate().isBefore(today));
    }

    public double getNumberOfMinuteHappenToday() {
        assert happensToday() : "should only calculate number of minute happens today when the task happens today";
        double duration = this.duration.get().duration;
        if (dateTime.get().isToday()) {
            return duration;
        } else {
            LocalDateTime today = LocalDate.now().atStartOfDay();
            return MINUTES.between(today, dateTime.get().dateTime.plusMinutes((long) duration));
        }
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(this.getTitle())
                && otherTask.getDescription().equals(this.getDescription())
                && otherTask.dateTime.equals(this.dateTime)
                && otherTask.duration.equals(this.duration);
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, dateTime, duration);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitleString())
                .append(getDescriptionString())
                .append(getDateTimeString())
                .append(getDurationString());
        return builder.toString();
    }
}
