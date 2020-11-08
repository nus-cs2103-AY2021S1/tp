package seedu.address.model.timetable;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    UNKNOWN;

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only be one of the following: Monday, Tuesday, Wednesday, Thursday, Friday";

    private static final Map<String, Day> MAPPING = new HashMap<>();

    private final String day;

    Day() {
        day = null;
    }

    Day(String day) {
        this.day = day;
    }

    static {
        for (Day day : Day.values()) {
            MAPPING.put(day.day, day);
        }
    }

    public String getDay() {
        return day;
    }

    public static Day getDayEnum(String day) {
        Day dayEnum = MAPPING.get(day);
        if (dayEnum == null) {
            return UNKNOWN;
        }
        return dayEnum;
    }

    /**
     * Returns true if this day is the same as the other day.
     * @param otherDay The other day to be checked.
     * @return true if this day is the same as the other day.
     */
    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        }

        return otherDay != null
                && otherDay.getDay().equals(day);
    }

    public static boolean isUnknownDay(Day day) {
        return day.equals(UNKNOWN);
    }

    @Override
    public String toString() {
        assert day != null;
        char firstLetterToUpperCase = day.substring(0, 1).toUpperCase().charAt(0);
        return day.replace(day.charAt(0), firstLetterToUpperCase);
    }
}
