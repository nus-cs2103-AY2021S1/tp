package seedu.pivot.model.investigationcase.caseperson;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's gender in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)} (String)}
 */
public enum Gender {
    M, F;

    public static final String MESSAGE_CONSTRAINTS = "Gender can only be either M or F";

    /**
     * Creates a Gender Enum with given gender String.
     * @param gender
     * @return
     */
    public static Gender createGender(String gender) {
        requireNonNull(gender);
        return Gender.valueOf(gender.trim().toUpperCase());
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        String trimmedTest = test.trim();
        for (Gender gender : Gender.values()) {
            if (gender.name().equals(trimmedTest.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
