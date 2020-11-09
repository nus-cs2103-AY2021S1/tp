package seedu.address.model.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's blacklist status (whether they have been blacklisted).
 * Guarantees: immutable; is valid as declared in {@link #isValidBlacklistStatus(String)}
 */
public class BlacklistStatus {

    public static final String MESSAGE_CONSTRAINTS = "Blacklisted status must be either \'true\' or \'false\'."
            + " It is not case-sensitive.";


    public final boolean isBlacklisted;

    /**
     * Constructs an {@code Blacklist}.
     *
     * @param isBlacklisted A string representing .
     */
    public BlacklistStatus(String isBlacklisted) {
        requireNonNull(isBlacklisted);
        checkArgument(isValidBlacklistStatus(isBlacklisted), MESSAGE_CONSTRAINTS);
        this.isBlacklisted = Boolean.parseBoolean(isBlacklisted.trim());
    }

    /**
     * Returns if a given String represents a valid Blacklist status.
     */
    public static boolean isValidBlacklistStatus(String isBlacklisted) {
        isBlacklisted = isBlacklisted.trim().toLowerCase();
        return isBlacklisted.equals("true") || isBlacklisted.equals("false");
    }

    @Override
    public String toString() {
        return Boolean.valueOf(isBlacklisted).toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BlacklistStatus // instanceof handles nulls
                && isBlacklisted == ((BlacklistStatus) other).isBlacklisted); // state check
    }

    @Override
    public int hashCode() {
        return Boolean.valueOf(isBlacklisted).hashCode();
    }
}
