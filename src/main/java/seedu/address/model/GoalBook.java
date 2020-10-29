package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import javafx.collections.ObservableMap;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.UniqueGoalMap;
import seedu.address.model.goal.Goal;

public class GoalBook implements ReadOnlyGoalBook{

    private final UniqueGoalMap goalMap;

    {
        goalMap = new UniqueGoalMap();
    }

    public GoalBook() {
    }

    /**
     * Creates an GoalBook using the Goals in the {@code toBeCopied}
     */
    public GoalBook(ReadOnlyGoalBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// map overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGoals(Map<Date, Goal> goals) {
        this.goalMap.setGoals(goals);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGoalBook newData) {
        requireNonNull(newData);

        setGoals(newData.getGoalMap());
    }

    //// goal-level operations

    /**
     * Returns true if a goal with the same identity as {@code goal} exists in the goal book.
     */
    public boolean hasGoal(Goal goal) {
        requireNonNull(goal);
        return goalMap.contains(goal);
    }

    /**
     * Adds a goal to the exercise book.
     * The goal must not already exist in the goal book.
     */
    public void addGoal(Goal goal) {
        goalMap.add(goal);
    }

    /**
     * Replaces the given goal {@code target} in the list with {@code editedGoal}.
     * {@code target} must exist in the exercise book.
     * The person identity of {@code editedGoal} must not be the same as another existing goal in the goal book.
     */
    public void setGoal(Goal target, Goal editedGoal) {
        requireNonNull(editedGoal);
        goalMap.setGoal(target, editedGoal);
    }

    /**
     * Removes {@code key} from this {@code GoalBook}.
     * {@code key} must exist in the goal book.
     */
    public void removeGoal(Goal key) {
        goalMap.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return goalMap.asUnmodifiableObservableMap().size() + " goals";
        // TODO: refine later
    }

    @Override
    public ObservableMap<Date,Goal> getGoalMap() {
        return goalMap.asUnmodifiableObservableMap();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoalBook // instanceof handles nulls
                && goalMap.equals(((GoalBook) other).goalMap));
    }

    @Override
    public int hashCode() {
        return goalMap.hashCode();
    }

}
