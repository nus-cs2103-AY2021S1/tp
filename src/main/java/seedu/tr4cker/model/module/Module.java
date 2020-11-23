package seedu.tr4cker.model.module;

import static seedu.tr4cker.commons.util.AppUtil.checkArgument;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Module in TR4CKER.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleName(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS =
            "Module names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String moduleName;
    public final ModuleCode moduleCode;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     * @param moduleCode A valid module code.
     */
    public Module(String moduleName, ModuleCode moduleCode) {
        requireAllNonNull(moduleName, moduleCode);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both modules have the same module code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && moduleCode.equals(otherModule.moduleCode);
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && moduleName.equals(((Module) other).moduleName)
                && moduleCode.equals(((Module) other).moduleCode));
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[ " + moduleCode.toString() + " | " + moduleName + " ]";
    }
}
