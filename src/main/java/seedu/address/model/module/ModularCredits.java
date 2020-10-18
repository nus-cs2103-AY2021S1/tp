package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ModularCredits {
    public static final String MESSAGE_CONSTRAINTS =
            "Modular Credits should be in the form of an number and cannot be less than zero.";
    public static final double DEFAULT_MODULAR_CREDITS = 4.0;
    public final double moduleCredits;
    /**
     * Constructs a {@code ModularCredits}.
     *
     * @param modularCredits A number in string form
     */
    public ModularCredits(String modularCredits) {
        requireNonNull(modularCredits);
        double convertedModuleCredits;
        try {
            convertedModuleCredits = Double.parseDouble(modularCredits);
            checkArgument(isValidModularCredits(convertedModuleCredits), MESSAGE_CONSTRAINTS);
        } catch (NumberFormatException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
            convertedModuleCredits = DEFAULT_MODULAR_CREDITS;
        }
        this.moduleCredits = convertedModuleCredits;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidModularCredits(double test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return Double.toString(moduleCredits);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleName // instanceof handles nulls
                && moduleCredits == (((ModularCredits) other).moduleCredits)); // state check
    }

}
