package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.exercise.exceptions.DuplicateExerciseException;
import seedu.address.model.exercise.exceptions.DuplicateGoalException;
import seedu.address.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.address.model.exercise.exceptions.GoalNotFoundException;
import seedu.address.model.goal.Goal;

public class UniqueGoalMap {
    private final ObservableMap<Date, Goal> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<Date, Goal> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);


    /**
     * Returns true if the map contains an equivalent goal as the given argument.
     */
    public boolean contains(Goal toCheck) {
        requireNonNull(toCheck);
        return internalMap.containsKey(toCheck.date);
    }

    /**
     * Adds a goal to the map.
     * There should not exist another goal with the same date.
     */
    public void add(Goal toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExerciseException();
        }
        internalMap.put(toAdd.date,toAdd);
    }

    /**
     * Replaces the goal {@code target} in the map with {@code editedGoal}.
     * {@code target} must exist in the map.
     */
    public void setGoal(Goal target, Goal editedGoal) {
        requireAllNonNull(target, editedGoal);
        
        if (!internalMap.containsKey(target.date)) {
            throw new GoalNotFoundException();
        }

        if (!target.isSameGoal(editedGoal) && contains(editedGoal)) {
            throw new DuplicateGoalException();
        }

        internalMap.put(editedGoal.getDate(), editedGoal);
    }

    /**
     * Removes the equivalent goal from the map.
     * The goal must exist in the map.
     */
    public void remove(Goal toRemove) {
        requireNonNull(toRemove);
        try {
            internalMap.remove(toRemove.getDate());
        } catch (Exception e) {
            throw new GoalNotFoundException();
        }
    }
    

    public void setGoals(UniqueGoalMap replacement) {
        requireNonNull(replacement);
        internalMap.clear();
        for (Map.Entry<Date,Goal> entry : replacement.internalMap.entrySet()) {
            internalMap.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */

    public void setGoals(Map<Date,Goal> goals) {
        requireAllNonNull(goals);

        for (Map.Entry<Date,Goal> entry : goals.entrySet()) {
            internalMap.put(entry.getKey(), entry.getValue());
        }
    }
    /**
     * Returns the backing map as an unmodifiable {@code ObservableMap}.
     */
    public ObservableMap<Date,Goal> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGoalMap // instanceof handles nulls
                && internalMap.equals(((UniqueGoalMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

}