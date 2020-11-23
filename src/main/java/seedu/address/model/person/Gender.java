package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the HairStyleX.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public enum GenderEnum {
        Male, Female;
    }

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should be either F or M or f or m. Auto-converts to uppercase for readability";
    public static final String VALIDATION_REGEX = "[MFmf]";
    public static final Gender MALE_GENDER = new Gender("M");
    public static final Gender FEMALE_GENDER = new Gender("F");
    public final GenderEnum value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param str A valid gender string.
     */
    public Gender(String str) {
        requireNonNull(str);
        checkArgument(isValidGender(str), MESSAGE_CONSTRAINTS);
        switch (str.toUpperCase()) {
        case "M":
            value = GenderEnum.Male;
            break;
        case "F":
            value = GenderEnum.Female;
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns true if a given string is a valid gender number.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        switch (value) {
        case Male:
            return "M";
        case Female:
            return "F";
        default:
            throw new RuntimeException();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Gender // instanceof handles nulls
            && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

