package seedu.address.model.goal;

import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;

/**
 * Creates a goal in the exercise Book
 * Guarantees: immutable;
 */
public class Goal {

    public final Calories goal;
    public final Date date;

    /**
     * Constructs a {@code Goal}.
     *
     * @param goal A Calorie goal.
     */
    public Goal(Calories goal, Date date) {
        this.goal = goal;
        this.date = date;
    }

    /**
     * Creates a default goal with 0 calories at a particular date.
     * @param date
     */
    public Goal(Date date) throws CaloriesOverflow {
        this.goal = new Calories("0");
        this.date = date;
    }

    public Goal updateGoal (Calories calorie) throws CaloriesOverflow {
        return new Goal(goal.subtract(calorie), this.date);
    }

    public Date getDate() {
        return date;
    }

    public Calories getCalories() {
        return goal;
    }

    @Override
    public String toString() {
        return goal.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.goal.Goal // instanceof handles nulls
                && goal.equals(((Goal) other).goal)
                && date.equals(((Goal) other).date)); // state check
    }

    /**
     * Returns true if both goals have the same date
     */
    public boolean isSameGoal(Goal otherGoal) {
        if (otherGoal == this) {
            return true;
        }
        return otherGoal != null
                && (otherGoal.getDate().equals(getDate()));
    }

    @Override
    public int hashCode() {
        return goal.hashCode();
    }
}
