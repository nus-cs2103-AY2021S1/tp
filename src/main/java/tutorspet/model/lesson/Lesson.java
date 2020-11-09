package tutorspet.model.lesson;

import static tutorspet.commons.util.AppUtil.checkArgument;
import static tutorspet.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import tutorspet.model.attendance.AttendanceRecordList;

/**
 * Represents a Lesson.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String MESSAGE_CONSTRAINTS = "Start time must be earlier than end time.";

    // identity fields
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Day day;
    private final Venue venue;

    // data fields
    private final NumberOfOccurrences numberOfOccurrences;
    private final AttendanceRecordList attendanceRecordList;

    /**
     * Every field must be present and not null.
     * Creates a new lesson with the specified parameters.
     */
    public Lesson(LocalTime startTime, LocalTime endTime, Day day, NumberOfOccurrences numberOfOccurrences,
                  Venue venue) {
        requireAllNonNull(startTime, endTime, day, numberOfOccurrences, venue);
        checkArgument(isValidStartTimeEndTime(startTime, endTime));

        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.numberOfOccurrences = numberOfOccurrences;
        this.venue = venue;
        this.attendanceRecordList = new AttendanceRecordList(numberOfOccurrences);
    }

    /**
     * Every field must be present and not null.
     * Creates a new lesson with the specified parameters.
     */
    public Lesson(LocalTime startTime, LocalTime endTime, Day day, NumberOfOccurrences numberOfOccurrences,
                  Venue venue, AttendanceRecordList attendanceRecordList) {
        requireAllNonNull(startTime, endTime, day, numberOfOccurrences, venue, attendanceRecordList);
        checkArgument(isValidStartTimeEndTime(startTime, endTime));

        assert attendanceRecordList.getAttendanceRecordList().size() == numberOfOccurrences.getNumberOfOccurrences();

        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.numberOfOccurrences = numberOfOccurrences;
        this.venue = venue;
        this.attendanceRecordList = attendanceRecordList;
    }

    /**
     * Returns true if the startTime is earlier than endTime.
     */
    public static boolean isValidStartTimeEndTime(LocalTime startTime, LocalTime endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Day getDay() {
        return day;
    }

    public NumberOfOccurrences getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public Venue getVenue() {
        return venue;
    }

    public AttendanceRecordList getAttendanceRecordList() {
        return attendanceRecordList;
    }

    /**
     * Returns true if both lessons have the same start time, end time and day.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getDay().equals(getDay());
    }

    /**
     * Returns true if the timing of both lessons overlap.
     */
    public boolean isOverlapLesson(Lesson otherLesson) {
        return getDay().equals(otherLesson.getDay())
                && getStartTime().compareTo(otherLesson.getEndTime()) < 0
                && getEndTime().compareTo(otherLesson.getStartTime()) > 0;
    }

    /**
     * Returns a print friendly version of the {@code Lesson} object.
     */
    public String printLesson() {
        return this.day.toString() + " " + this.startTime.toString() + " to " + this.endTime.toString();
    }

    /**
     * Returns true if both lessons have same start time, end time, day, number of occurrences and venue.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;

        return otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getDay().equals(getDay())
                && otherLesson.getNumberOfOccurrences().equals(getNumberOfOccurrences())
                && otherLesson.getVenue().equals(getVenue())
                && otherLesson.getAttendanceRecordList().equals(getAttendanceRecordList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startTime, endTime, day, numberOfOccurrences, venue, attendanceRecordList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(day)
                .append(" ")
                .append(TIME_FORMATTER.format(getStartTime()))
                .append(" to ")
                .append(TIME_FORMATTER.format(getEndTime()))
                .append("\n")
                .append("Venue: ")
                .append(getVenue())
                .append(" Number of occurrences: ")
                .append(getNumberOfOccurrences());
        return builder.toString();
    }
}
