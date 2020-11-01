package seedu.zookeep.model.medicalcondition;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.commons.util.AppUtil.checkArgument;

/**
 * Represents a MedicalCondition in the ZooKeep Book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMedicalConditionName(String)}
 */
public class MedicalCondition {

    public static final String MESSAGE_CONSTRAINTS =
            "Medical condition names should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String medicalConditionName;

    /**
     * Constructs a {@code MedicalCondition}.
     *
     * @param medicalConditionName A valid medicalCondition name.
     */
    public MedicalCondition(String medicalConditionName) {
        requireNonNull(medicalConditionName);
        checkArgument(isValidMedicalConditionName(medicalConditionName), MESSAGE_CONSTRAINTS);
        this.medicalConditionName = medicalConditionName;
    }

    /**
     * Returns true if a given string is a valid medicalCondition name.
     */
    public static boolean isValidMedicalConditionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalCondition // instanceof handles nulls
                && medicalConditionName.toLowerCase().equals(((MedicalCondition) other)
                .medicalConditionName.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return medicalConditionName.toLowerCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + medicalConditionName + ']';
    }

}
