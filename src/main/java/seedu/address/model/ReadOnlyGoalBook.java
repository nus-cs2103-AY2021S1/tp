package seedu.address.model;

import javafx.collections.ObservableMap;
import seedu.address.model.exercise.Date;
import seedu.address.model.goal.Goal;

public interface ReadOnlyGoalBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableMap<Date, Goal> getGoalMap();
}
