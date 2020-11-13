package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;

/**
 * Jackson-friendly version of {@link seedu.address.model.calorie.Output}.
 */
class JsonAdaptedOutput {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Output's %s field is missing!";
    private final String calorieCount;
    private final String time;
    private final String exercise;

    /**
     * Constructs a {@code JsonAdaptedOutput} with the given Output details.
     */
    @JsonCreator
    public JsonAdaptedOutput(@JsonProperty("time") String time,
                             @JsonProperty("exercise") String exercise,
                             @JsonProperty("calorieCount") String calorieCount) {
        this.time = time;
        this.exercise = exercise;
        this.calorieCount = calorieCount;
    }

    /**
     * Converts a given {@code Output} into this class for Jackson use.
     */
    public JsonAdaptedOutput(Output source) {
        calorieCount = source.getCalorieCount().calorieCount;
        time = source.getTime().time;
        exercise = source.getExercise().exercise;
    }


    /**
     * Converts this Jackson-friendly adapted output object into the model's {@code Output} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted output.
     */
    public Output toModelType() throws IllegalValueException {
        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(String.format(Time.MESSAGE_CONSTRAINTS));
        }
        final Time modelTime = new Time(time);

        if (exercise == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Exercise.class.getSimpleName()));
        }
        if (!Exercise.isValidExercise(exercise)) {
            throw new IllegalValueException(String.format(Exercise.MESSAGE_CONSTRAINTS));
        }
        final Exercise modelExercise = new Exercise(exercise);

        if (calorieCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalorieCount.class.getSimpleName()));
        }
        if (!CalorieCount.isValidCalorieCount(calorieCount)) {
            throw new IllegalValueException(String.format(CalorieCount.MESSAGE_CONSTRAINTS));
        }
        final CalorieCount modelCalorieCount = new CalorieCount(calorieCount);
        return new Output(modelTime, modelExercise, modelCalorieCount);
    }

}
