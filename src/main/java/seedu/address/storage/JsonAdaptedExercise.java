package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.exercise.Name;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise's %s field is missing!";

    private final String name;
    private final String description;
    private final String date;
    private final String calories;
    private final List<JsonAdaptedMuscleTag> musclesTagged = new ArrayList<>();
    private final List<JsonAdaptedExerciseTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given exercise details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("description") String description,
                               @JsonProperty("date") String date, @JsonProperty("calories") String calories,
                               @JsonProperty("muscleTags") List<JsonAdaptedMuscleTag> musclesTagged,
                               @JsonProperty("tags") List<JsonAdaptedExerciseTag> tagged) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
        if (musclesTagged != null) {
            this.musclesTagged.addAll(musclesTagged);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code JsonAdaptedExercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().fullName;
        description = source.getDescription().value;
        date = source.getDate().value;
        calories = source.getCalories().toString();
        musclesTagged.addAll(source.getMuscleTags().stream()
                .map(JsonAdaptedMuscleTag::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getExerciseTags().stream()
                .map(JsonAdaptedExerciseTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
        final List<ExerciseTag> exerciseTags = new ArrayList<>();
        final List<MuscleTag> muscleTags = new ArrayList<>();

        for (JsonAdaptedExerciseTag tag : tagged) {
            exerciseTags.add(tag.toModelType());
        }
        for (JsonAdaptedMuscleTag tag : musclesTagged) {
            muscleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        final Calories modelCalories;
        if (calories == null) {
            modelCalories = null;
        } else if (!Calories.isValidCalories(calories)) {
            throw new IllegalValueException(Calories.MESSAGE_CONSTRAINTS);
        } else {
            modelCalories = new Calories(calories);
        }

        final Set<MuscleTag> musclesWorkedTags = new HashSet<>(muscleTags);

        final Set<ExerciseTag> modelTags = new HashSet<>(exerciseTags);

        return new Exercise(modelName, modelDescription, modelDate, modelCalories, musclesWorkedTags, modelTags);
    }
}

