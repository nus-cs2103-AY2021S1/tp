package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.MuscleTag;

/**
 * Jackson-friendly version of {@link MuscleTag}.
 */
class JsonAdaptedMuscleTag {

    private final String muscleTagName;

    /**
     * Constructs a {@code JsonAdaptedMuscleTag} with the given {@code muscleTagName}.
     */
    @JsonCreator
    public JsonAdaptedMuscleTag(String muscleTagName) {
        this.muscleTagName = muscleTagName;
    }

    /**
     * Converts a given {@code MuscleTag} into this class for Jackson use.
     */
    public JsonAdaptedMuscleTag(MuscleTag source) {
        muscleTagName = source.muscleTagName;
    }

    @JsonValue
    public String getMuscleTagName() {
        return muscleTagName;
    }

    /**
     * Converts this Jackson-friendly adapted muscleTag object into the model's {@code MuscleTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted muscleTag.
     */
    public MuscleTag toModelType() throws IllegalValueException {
        if (!MuscleTag.isValidMuscleTagName(muscleTagName)) {
            throw new IllegalValueException(MuscleTag.MESSAGE_CONSTRAINTS);
        }
        return new MuscleTag(muscleTagName);
    }

}
