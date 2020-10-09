package seedu.address.model.log;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.exercise.Exercise;

/**
 * Represents a Log in the address book. Guarantees: details are present and not null, field values
 * are validated, immutable.
 */
public class Log {

    // Identity fields
    private final Exercise exercise;
    private final LocalDateTime dateTime;

    // Data fields
    private final Rep reps;
    private final Comment comment;

    /**
     * Every field must be present and not null.
     */
    public Log(Exercise exercise, Rep reps, Comment comment) {
        requireAllNonNull(exercise);
        this.exercise = exercise;
        this.dateTime = LocalDateTime.now();
        this.reps = reps;
        this.comment = comment;
    }

    /**
     * Constructor used to get data from files
     */
    public Log(Exercise exercise, Rep reps, Comment comment, LocalDateTime dateTime) {
        requireAllNonNull(exercise, reps, comment, dateTime);
        this.exercise = exercise;
        this.dateTime = dateTime;
        this.reps = reps;
        this.comment = comment;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets date time in string format
     *
     * @return String representing date time of log. Example format: Sat 3.01pm, 03 Oct 2020
     */
    public String getPrettyDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE h:mma, dd MMM yyyy");
        return formatter.format(dateTime);
    }

    public Rep getReps() {
        return reps;
    }

    public Comment getComment() {
        return comment;
    }

    public int getCalories() {
        return exercise.getCaloriesPerRep() * reps.getReps();
    }

    /**
     * Returns true if both logs of the same exercise have the same dateTime. This defines a weaker
     * notion of equality between two persons.
     */
    public boolean isSameLog(Log otherLog) {
        if (otherLog == this) {
            return true;
        }

        return otherLog != null
                && otherLog.getExercise().equals(getExercise())
                && (otherLog.getDateTime().equals(getDateTime()));
    }

    /**
     * Returns true if both logs have the same identity and data fields. This defines a stronger
     * notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Log)) {
            return false;
        }

        Log otherLog = (Log) other;

        return otherLog.getExercise().equals(getExercise())
                && otherLog.getDateTime().equals(getDateTime())
                && otherLog.getReps().equals(getReps())
                && otherLog.getComment().equals(getComment());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(exercise, dateTime, reps, comment);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append("Exercise: ")
                .append(getExercise())
                .append(" DateTime: ")
                .append(getDateTime())
                .append(" Rep: ")
                .append(getReps())
                .append(" Comments: ")
                .append(getComment());

        return builder.toString();
    }
}
