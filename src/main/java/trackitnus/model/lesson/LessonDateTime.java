package trackitnus.model.lesson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import trackitnus.commons.util.CollectionUtil;


public class LessonDateTime {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    private final DayOfWeek weekday;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Every field must be present and not null.
     *
     * @param weekday
     * @param startTime
     * @param endTime
     */
    public LessonDateTime(DayOfWeek weekday, LocalTime startTime, LocalTime endTime) {
        CollectionUtil.requireAllNonNull(weekday, startTime, endTime);
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getWeekday() {
        return this.weekday;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Compares this LessonDateTime with another LessonDateTime in chronological order.
     *
     * @param other The other LessonDateTime to compare.
     * @return an int < 0 if this is "less than" other.
     */
    public int compareTo(LessonDateTime other) {
        if (equals(other)) {
            return 0;
        } else {
            LocalDate currentDate = LocalDate.now(Lesson.DEFAULT_TIME_ZONE);
            DayOfWeek currentWeekday = DayOfWeek.getLessonWeekDay(currentDate);
            Integer currentToThis = DayOfWeek.distanceBetweenTwoDay(currentWeekday, this.getWeekday());
            Integer currentToOther = DayOfWeek.distanceBetweenTwoDay(currentWeekday, other.getWeekday());
            int result = currentToThis.compareTo(currentToOther);
            return result == 0
                ? this.getStartTime().compareTo(other.getStartTime())
                : result;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LessonDateTime)) {
            return false;
        }

        LessonDateTime otherDate = (LessonDateTime) other;
        return otherDate.weekday.equals(weekday)
            && otherDate.startTime.equals(startTime)
            && otherDate.endTime.equals(endTime);
    }

    @Override
    public String toString() {
        return weekday.name() + " "
            + startTime.format(FORMATTER) + "-"
            + endTime.format(FORMATTER);
    }
}
