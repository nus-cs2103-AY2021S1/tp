package seedu.pivot.model.investigationcase.caseperson;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's sex in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidSex(String)} (String)}
 */
public enum Sex {
    M, F;

    public static final String MESSAGE_CONSTRAINTS = "Sex can only be either M or F";

    /**
     * Creates a Sex Enum with given sex String.
     * @param sex
     * @return
     */
    public static Sex createSex(String sex) {
        requireNonNull(sex);
        return Sex.valueOf(sex.trim().toUpperCase());
    }

    /**
     * Returns true if a given string is a valid sex.
     */
    public static boolean isValidSex(String test) {
        String trimmedTest = test.trim();
        for (Sex sex : Sex.values()) {
            if (sex.name().equals(trimmedTest.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
