package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

/**
 * Represent's a Patient's blood type in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS = "Blood type can only be one of the 8 possible blood types";
    public final BloodTypeEnum bloodType;

    /**
     * Constructor for BloodType object
     * @param bloodType blood type of the patient
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        this.bloodType = BloodTypeEnum.valueOfLabel(bloodType);
    }

    public static boolean isValidBloodType(String test) {
        return !(BloodTypeEnum.valueOfLabel(test) == null);
    }

    @Override
    public String toString() {
        return bloodType.label;
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof BloodType
                && bloodType.equals(((BloodType) other).bloodType));
    }
}
