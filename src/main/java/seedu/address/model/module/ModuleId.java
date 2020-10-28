package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's ID in the module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleId(String)}
 */
public class ModuleId {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public String id;

    /**
     * Constructs a {@code ModuleId}.
     *
     * @param id A valid id.
     */
    public ModuleId(String id) {
        requireNonNull(id);
        checkArgument(isValidModuleId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidModuleId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void setId(String newId) {
        this.id = newId;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleId // instanceof handles nulls
                && id.equals(((ModuleId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
