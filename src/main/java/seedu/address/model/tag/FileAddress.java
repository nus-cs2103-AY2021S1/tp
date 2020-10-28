package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's file address in the Hello File.
 * Guarantees: immutable; is valid as declared in {@link #isValidFileAddress(String)}
 */
public class FileAddress {

    public static final String MESSAGE_CONSTRAINTS = "File address can only take a valid file path!";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([a-zA-z])?:?([\\\\/\\s[a-zA-Z0-9]+\\.]*)?";

    public final String value;

    /**
     * Constructs an {@code FileAddress}.
     *
     * @param address A valid address.
     */
    public FileAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidFileAddress(address), MESSAGE_CONSTRAINTS);
        value = formatByOS(address);
    }

    /**
     * Returns true if a given string is a valid file address.
     */
    public static boolean isValidFileAddress(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() != 0 && !test.matches("\\s+");
    }

    /**
     * Formats a string to be specific to operating system.
     *
     * @param toFormat the string before formatting
     * @return the string after formatting
     */
    private String formatByOS(String toFormat) {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            return toFormat.replace('/', '\\');
        } else {
            return toFormat.replace('\\', '/');
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileAddress // instanceof handles nulls
                && value.equals(((FileAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
