package seedu.address.model.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Represents a Person's url link to his/her personal profile page.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class UrlLink {


    public static final String MESSAGE_CONSTRAINTS =
            "Link should be a valid URL link, and it should not exceed 350 characters in length.";

    /**
     * UrlValidator used to check if url format is valid;
     */
    private static final UrlValidator validator = new UrlValidator() {
        @Override
        public boolean isValid(String value) {
            // override method so that links without a scheme will still be valid
            // as long as the rest of the format is correct
            // i.e link does not need to have http/https/ftp scheme in front
            return super.isValid(value) || super.isValid("http://" + value);
        }
    };

    public final String value;

    /**
     * Constructs a {@code UrlLink}.
     *
     * @param link A valid url link.
     */
    public UrlLink(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        value = link;
    }

    /**
     * Returns true if a given string is a valid link.
     * A string is considered valid if it has the correct format.
     * This method does not check if connection can be made with the link
     * since this app does not make use of internet.
     */
    public static boolean isValidLink(String test) {
        requireNonNull(test);
        return validator.isValid(test) && test.length() <= 350;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UrlLink // instanceof handles nulls
                && value.equals(((UrlLink) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
