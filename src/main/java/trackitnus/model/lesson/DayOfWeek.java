package trackitnus.model.lesson;

import java.time.LocalDate;

public enum DayOfWeek {
    Sun,
    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat;
    private static final int SIZE = 7;

    public static DayOfWeek getLessonWeekDay(LocalDate date) {
        java.time.DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
        case SUNDAY:
            return Sun;
        case MONDAY:
            return Mon;
        case TUESDAY:
            return Tue;
        case WEDNESDAY:
            return Wed;
        case THURSDAY:
            return Thu;
        case FRIDAY:
            return Fri;
        case SATURDAY:
            return Sat;
        default:
            throw new AssertionError(
                "Unexpected error: Unable to retrieve weekday from LocalDate.");
        }
    }

    public static Integer distanceBetweenTwoDay(DayOfWeek from, DayOfWeek to) {
        return (to.ordinal() - from.ordinal() + SIZE) % SIZE;
    }
}
