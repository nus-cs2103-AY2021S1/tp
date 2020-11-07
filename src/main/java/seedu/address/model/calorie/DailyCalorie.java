package seedu.address.model.calorie;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;


/**
 * Represents a DailyCalorie in fitNUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DailyCalorie implements Comparable<DailyCalorie> {

    // Identity fields
    private final LocalDate date;
    private int calorieSum = 0;

    /**
     * Every field must be present and not null.
     */
    public DailyCalorie(LocalDate date) {
        requireAllNonNull(date);
        this.date = date;
    }

    /**
     * Returns the Date that this DailyCalorie is representing.
     * @return Date that the object is representing.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the calorie sum for the day.
     */
    public int getCalories() {
        return calorieSum;
    }

    /**
     * Adds the calories to the calorie sum.
     */
    public void addCalories(int calorieAddition) {
        calorieSum += calorieAddition;
    }

    /**
     * Minus the calories to the calorie sum.
     */
    public void minusCalories(int calories) {
        assert(calorieSum > 0);
        calorieSum -= calories;
    }


    /**
     * Returns true if both daily calorie log has the same date attached to it.
     * This defines a weaker notion of equality between two daily calorie log.
     */
    public boolean isSameDailyCalorie(DailyCalorie otherDailyCalorie) {
        if (otherDailyCalorie == this) {
            return true;
        }

        return otherDailyCalorie != null
                && otherDailyCalorie.getDate().equals(getDate());
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
     * This defines a stronger notion of equality between two daily calorie logs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DailyCalorie)) {
            return false;
        }

        DailyCalorie otherDailyCalorie = (DailyCalorie) other;
        return otherDailyCalorie.getDate().equals(getDate())
                && otherDailyCalorie.getCalories() == (getCalories());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, calorieSum);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Daily calorie for: ")
                .append(getDate())
                .append(" Calories: ")
                .append(calorieSum);
        return builder.toString();
    }

    @Override
    public int compareTo(DailyCalorie o) {
        return this.getDate().compareTo(o.getDate());
    }
}
