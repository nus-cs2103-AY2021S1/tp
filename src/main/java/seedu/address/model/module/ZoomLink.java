package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's Zoom link in the Cap5.0Buddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidZoomLink(String)}
 */
public class ZoomLink {

    public static final String MESSAGE_CONSTRAINTS = "Zoom link can take any values, and it should not be blank";

    /*
     * The first character of the Zoom link must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ZoomLink}.
     *
     * @param zoomLink A valid Zoom link.
     */
    public ZoomLink(String zoomLink) {
        requireNonNull(zoomLink);
        checkArgument(isValidZoomLink(zoomLink), MESSAGE_CONSTRAINTS);
        value = zoomLink;
    }

    /**
     * Returns true if a given string is a valid Zoom link.
     */
    public static boolean isValidZoomLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ZoomLink // instanceof handles nulls
                && value.equals(((ZoomLink) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
