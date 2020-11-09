package seedu.address.model.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's company's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class CompanyName {

    public static final String MESSAGE_CONSTRAINTS = "Company names can can contain any type of characters. "
            + "It should not be blank, and it should be 1 to 100 characters long.";

    public static final String VALIDATION_REGEX = "[\\S].{0,99}";

    public final String fullCompanyName;

    /**
     * Constructs a {@code Company Name}.
     *
     * @param name A valid company name.
     */
    public CompanyName(String name) {
        requireNonNull(name);
        checkArgument(isValidCompanyName(name), MESSAGE_CONSTRAINTS);
        fullCompanyName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCompanyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && fullCompanyName.equals(((CompanyName) other).fullCompanyName)); // state check
    }

    @Override
    public int hashCode() {
        return fullCompanyName.hashCode();
    }

}
