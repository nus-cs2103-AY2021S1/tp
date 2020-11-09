package seedu.zookeep.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Jackson-friendly version of {@link MedicalCondition}.
 */
class JsonAdaptedMedicalCondition {

    private final String medicalConditionName;

    /**
     * Constructs a {@code JsonAdaptedMedicalCondition} with the given {@code medicalConditionName}.
     */
    @JsonCreator
    public JsonAdaptedMedicalCondition(String medicalConditionName) {
        this.medicalConditionName = medicalConditionName;
    }

    /**
     * Converts a given {@code MedicalCondition} into this class for Jackson use.
     */
    public JsonAdaptedMedicalCondition(MedicalCondition source) {
        medicalConditionName = source.medicalConditionName;
    }

    @JsonValue
    public String getMedicalConditionName() {
        return medicalConditionName;
    }

    /**
     * Converts this Jackson-friendly adapted medicalCondition object into the model's {@code MedicalCondition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medicalCondition.
     */
    public MedicalCondition toModelType() throws IllegalValueException {
        if (!MedicalCondition.isValidMedicalConditionName(medicalConditionName)) {
            throw new IllegalValueException(MedicalCondition.MESSAGE_CONSTRAINTS);
        }
        return new MedicalCondition(medicalConditionName);
    }

}
