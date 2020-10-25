package seedu.pivot.model.investigationcase.caseperson;

/**
 * Represents a Person's gender in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)} (String)}
 */
public enum Gender {
    M, F;

    public static final String MESSAGE_CONSTRAINTS = "Gender can only be either M or F";

    public static Gender createGender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equals(test.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
