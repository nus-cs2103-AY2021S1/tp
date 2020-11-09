package seedu.address.model.module;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.ModuleInfoRetriever;

/**
 * Represent a Module's modular credits in my mods.
 * Guarantees: immutable; is valid as declared in {@link #isValidModularCredit(int)}
 */
public class ModularCredit {
    public static final String MESSAGE_INVALID_MODULE = "The module you have entered is not within our database."
            + "\nOur database is only valid up to AY19/20."
            + "\nTo add this module, use the optional parameter mc/MODULAR_CREDITS to manually add this module!"
            + "\nMODULAR_CREDITS should be an integer between 1 and 16.";
    public static final String MESSAGE_INVALID_MODULAR_CREDIT = "Modular Credits (MCs) should only "
            + "contain integers between 1 and 16.";
    private static final int MINIMUM_MODULE_MC = 1;
    private static final int MAXIMUM_MODULE_MC = 16;

    public final int modularCredit;

    /**
     * Construct a {@code ModularCredit}.
     *
     * @param modularCredit A valid modular credit.
     */
    public ModularCredit(int modularCredit) {
        requireNonNull(modularCredit);
        this.modularCredit = modularCredit;
    }

    /**
     * ModularCredit field was not inputted.
     * Use the moduleName to retrieve relevant ModularCredit from JSON data.
     */
    public ModularCredit(String moduleName) throws ParseException {
        String modularCredit = ModuleInfoRetriever.retrieve(moduleName).get("moduleCredit");
        if (modularCredit.equals("N/A")) {
            throw new ParseException(ModularCredit.MESSAGE_INVALID_MODULE);
        }

        // Assumes modularCredit will be an integer
        int integerModularCredit = Integer.parseInt(modularCredit);
        assert(isValidModularCredit(integerModularCredit));
        this.modularCredit = integerModularCredit;
    }

    /**
     * Return true if a given int is a valid modular credit.
     */
    public static boolean isValidModularCredit(int test) {
        // Minimum and maximum modular credits a NUS module can have
        if (test >= MINIMUM_MODULE_MC && test <= MAXIMUM_MODULE_MC) {
            return true;
        } else {
            return false;
        }
    }
}
