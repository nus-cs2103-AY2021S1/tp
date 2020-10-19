package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Name;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String description;
    private final String date;
    private final String calories;

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("phone") String description,
                               @JsonProperty("email") String date, @JsonProperty("address") String calories) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;

    }

    /**
     * Converts a given {@code JsonAdaptedExercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().fullName;
        description = source.getDescription().value;
        date = source.getDate().value;
        calories = source.getCalories().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Exercise toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (description == null) {
            throw new IllegalValueException("Person's Description field is missing!");
            //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException("Invalid description");
            //throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (date == null) {
            throw new IllegalValueException("Person's Date field is missing!");
            //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException("Invalid Date");
            //throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (calories == null) {
            throw new IllegalValueException("Person's Calories field is missing!");
            //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
            // Address.class.getSimpleName()));
        }
        if (!Calories.isValidCalories(calories)) {
            throw new IllegalValueException("Invalid Calories");
            //throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Calories modelCalories = new Calories(calories);

        return new Exercise(modelName, modelDescription, modelDate, modelCalories);
    }

}
