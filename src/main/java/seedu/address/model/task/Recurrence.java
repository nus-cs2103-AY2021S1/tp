package seedu.address.model.task;

import java.time.temporal.ChronoUnit;

/**
 * Contains recurring properties of a task.
 */
public class Recurrence {
    public static final String DAY = "day";
    public static final String WEEK = "week";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    private final Integer value;
    private final ChronoUnit chronoUnit;

    /**
     * Converts the unit from a String to a ChronoUnit type, which is used later
     * for LocalDateTime operations.
     *
     * @param value magnitude of the recurrence
     * @param timePeriod unit of the magnitude (day/week/month/year)
     */
    public Recurrence(Integer value, String timePeriod) {
        this.value = value;

        assert this.value > 0 : "recurrence should not be 0";

        if (timePeriod.equals(DAY)) {
            this.chronoUnit = ChronoUnit.DAYS;
        } else if (timePeriod.equals(WEEK)) {
            this.chronoUnit = ChronoUnit.WEEKS;
        } else if (timePeriod.equals(MONTH)) {
            this.chronoUnit = ChronoUnit.MONTHS;
        } else if (timePeriod.equals(YEAR)) {
            this.chronoUnit = ChronoUnit.YEARS;
        } else {
            this.chronoUnit = null;
        }
    }

    public Integer getValue() {
        return this.value;
    }

    public ChronoUnit getChronoUnit() {
        return this.chronoUnit;
    }

    public String getUnit() {
        String rawChronoUnitToString = this.chronoUnit.toString();
        String unit = rawChronoUnitToString.substring(0, rawChronoUnitToString.length() - 1);
        unit += "(s)";
        return unit;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Recurrence) {
            Recurrence recur = (Recurrence) o;
            return this.value.equals(recur.value) && this.chronoUnit.equals(recur.chronoUnit);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + this.value + "," + this.chronoUnit.toString() + "]";
    }
}
