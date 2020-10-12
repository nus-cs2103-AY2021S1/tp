package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a ClassTime in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassTime(String)}
 */
public class ClassTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Class Time should follow the following format: {int: day_of_week} {int: start_time}-{int: end_time}";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String TIME_VALIDATION = "([01]?[0-9]|2[0-3])[0-5][0-9]";
    public static final String VALIDATION_REGEX = "([1-7])[\\s]" + TIME_VALIDATION + "-" + TIME_VALIDATION;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHm");
    public static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("HHmm");

    public final Integer dayOfWeek;
    public final LocalTime startTime;
    public final LocalTime endTime;

    /**
     * Constructs a {@code ClassTime}.
     *
     * @param inputTime A valid ClassTime.
     */
    public ClassTime(String inputTime) {
        requireNonNull(inputTime);
        checkArgument(isValidClassTime(inputTime), MESSAGE_CONSTRAINTS);
        this.dayOfWeek = extractDay(inputTime);
        this.startTime = extractStartTime(inputTime);
        this.endTime = extractEndTime(inputTime);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidClassTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static int extractDay(String input) {
        char day = input.charAt(0);
        return Integer.parseInt(String.valueOf(day));
    }

    private static LocalTime extractStartTime(String input) {
        String startTime = input.substring(2, 6);
        return LocalTime.parse(startTime, TIME_FORMATTER);
    }

    private static LocalTime extractEndTime(String input) {
        String endTime = input.substring(7);
        return LocalTime.parse(endTime, TIME_FORMATTER);
    }

    /**
     * Converts a {@code classTime} object back to a user input string
     */
    public String convertClassTimeToUserInputString() {
        String startTimeString = this.startTime.format(OUTPUT);
        String endTimeString = this.endTime.format(OUTPUT);
        String dayOfWeek = this.dayOfWeek.toString();
        final StringBuilder builder = new StringBuilder();
        builder.append(dayOfWeek)
                .append(" ")
                .append(startTimeString)
                .append("-")
                .append(endTimeString);
        return builder.toString();
    }

    @Override
    public String toString() {
        return String.format("Day of week: %s, Start time: %s, End Time: %s",
                this.dayOfWeek,
                this.startTime.format(TIME_FORMATTER),
                this.endTime.format(TIME_FORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassTime // instanceof handles nulls
                && dayOfWeek.equals(((ClassTime) other).dayOfWeek)
                && startTime.equals(((ClassTime) other).startTime)
                && endTime.equals(((ClassTime) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startTime, endTime);
    }

}
