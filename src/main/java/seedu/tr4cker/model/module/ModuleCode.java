package seedu.tr4cker.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in TR4CKER.
 * Guarantees: immutable; module code name is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS = "Module codes are case-insensitive, should only contain "
            + "alphanumeric characters, and it should not be blank or contain any spaces\n"
            + "\"DEL\" is not a valid module code";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String codeName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param codeName A valid module code.
     */
    public ModuleCode(String codeName) {
        requireNonNull(codeName);
        checkArgument(isValidModuleCode(codeName), MESSAGE_CONSTRAINTS);
        this.codeName = codeName.toUpperCase(); // All module codes are uppercase
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX)
                && !test.toLowerCase().equals("del"); // module code cannot be del
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && codeName.equals(((ModuleCode) other).codeName)); // state check
    }

    @Override
    public int hashCode() {
        return codeName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return codeName;
    }
}
