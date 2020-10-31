package seedu.cc.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

public class Name {
    public static final String MESSAGE_CONSTRAINTS = "Name can take in any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String accountName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.accountName = name;
    }

    /**
     * Returns true if a given string is a valid account name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return accountName;
    }

    @Override
    public String toString() {
        return this.accountName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && accountName.equals(((Name) other).accountName)); // state check
    }

    @Override
    public int hashCode() {
        return accountName.hashCode();
    }

}
