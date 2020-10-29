package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GoalBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.exercise.Date;
import seedu.address.model.goal.Goal;

/**
 * An Immutable GoalBook that is serializable to JSON format.
 */
@JsonRootName(value = "goalbook")
public class JsonSerializableGoalBook {
    public static final String MESSAGE_DUPLICATE_GOAL = "Goals list contains duplicate goal(s).";

    private final List<JsonAdaptedGoal> goals = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGoalBook} with the given goals.
     */
    @JsonCreator
    public JsonSerializableGoalBook(@JsonProperty("goals") List<JsonAdaptedGoal> goals) {
        this.goals.addAll(goals);
    }

    /**
     * Converts a given {@code ReadOnlyGoalBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGoalBook}.
     */
    public JsonSerializableGoalBook(ReadOnlyGoalBook source) {
        for(Map.Entry<Date,Goal> entry :source.getGoalMap().entrySet()) {
            goals.add(new JsonAdaptedGoal(entry.getValue()));
        }
    }

    /**
     * Converts this Goal book into the model's {@code GoalBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GoalBook toModelType() throws IllegalValueException {
        GoalBook goalBook = new GoalBook();
        for (JsonAdaptedGoal jsonAdaptedGoal : goals) {
            Goal goal = jsonAdaptedGoal.toModelType();
            if (goalBook.hasGoal(goal)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GOAL);
            }
            goalBook.addGoal(goal);
        }
        return goalBook;
    }
}
