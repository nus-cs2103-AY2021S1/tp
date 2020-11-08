package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.routine.Routine;
import seedu.address.model.util.Name;

/**
 * Jackson-friendly version of {@link Routine}.
 */
class JsonAdaptedRoutine {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Routine's %s field is missing!";

    private final String routineName;
    private final List<JsonAdaptedExercise> exercisesIncluded = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRoutine} with the given routine details.
     */
    @JsonCreator
    public JsonAdaptedRoutine(@JsonProperty("routineName") String routineName,
                             @JsonProperty("exercisesIncluded") List<JsonAdaptedExercise> tagged) {
        this.routineName = routineName;
        if (tagged != null) {
            this.exercisesIncluded.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Routine} into this class for Jackson use.
     */
    public JsonAdaptedRoutine(Routine source) {
        routineName = source.getName().fullName;
        exercisesIncluded.addAll(source.getExercises().stream()
                .map(JsonAdaptedExercise::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted routine object into the model's {@code Routine} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted routine.
     */
    public Routine toModelType() throws IllegalValueException {
        final List<Exercise> routineExercises = new ArrayList<>();
        for (JsonAdaptedExercise exercise : exercisesIncluded) {
            routineExercises.add(exercise.toModelType());
        }

        if (routineName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(routineName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS_FORMAT);
        }
        final Name modelName = new Name(routineName);

        final Set<Exercise> modelExercise = new HashSet<>(routineExercises);

        Routine modelRoutine = new Routine(modelName);
        for (Exercise exercise : modelExercise) {
            modelRoutine.addExercise(exercise);
        }
        return modelRoutine;
    }
}
