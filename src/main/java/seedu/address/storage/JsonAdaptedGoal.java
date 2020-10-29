package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Name;
import seedu.address.model.goal.Goal;

/**
 * Jackson-friendly version of {@link Goal}.
 */
public class JsonAdaptedGoal {
    
    private final String date;
    private final String calories;

    /**
     * Constructs a {@code JsonAdaptedGoal} with the given goal details.
     */
    @JsonCreator
    public JsonAdaptedGoal(@JsonProperty("date") String date, @JsonProperty("calories") String calories) {
        this.date = date;
        this.calories = calories;
    }

    /**
     * Converts a given {@code JsonAdaptedGoal} into this class for Jackson use.
     */
    public JsonAdaptedGoal(Goal source) {
        date = source.getDate().value;
        calories = source.getCalories().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Goal toModelType() throws IllegalValueException {
        
        if (date == null) {
            throw new IllegalValueException("Goal Date field is missing!");
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException("Invalid Date");
        }
        final Date modelDate = new Date(date);

        if (calories == null) {
            throw new IllegalValueException("Person's Calories field is missing!");
        }
        if (!Calories.isValidCalories(calories)) {
            throw new IllegalValueException("Invalid Calories");
        }
        final Calories modelCalories = new Calories(calories);

        return new Goal(modelCalories, modelDate);
    }    

}
