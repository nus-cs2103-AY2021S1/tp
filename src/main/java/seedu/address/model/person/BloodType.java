package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent's a Person's height in the address book.
 *
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS = "Blood type can only be one of the 8 possible blood types";
    public final BloodTypeEnum bloodType;

    /**
     * constructor for BloodType object
     * @param bloodType
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
