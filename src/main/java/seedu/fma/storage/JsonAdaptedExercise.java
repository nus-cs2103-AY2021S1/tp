package seedu.fma.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;



/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise's %s field is missing!";

    private final String name;
    private final int caloriesPerRep;

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given Exercise details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("caloriesPerRep") int caloriesPerRep) {
        this.name = name;
        this.caloriesPerRep = caloriesPerRep;
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().toString();
        caloriesPerRep = source.getCaloriesPerRep().value;
    }

    /**
     * Converts this Jackson-friendly adapted Exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Calories.isValidCalories(String.valueOf(caloriesPerRep))) {
            throw new IllegalValueException(Calories.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        return new Exercise(modelName, new Calories(caloriesPerRep));
    }
}
