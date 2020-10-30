package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's Zoom Link in the Module List.
 * Guarantees: immutable; is valid as declared in {@link #isValidZoomLink(String)}
 */
public class ZoomLink {

    public static final String MESSAGE_CONSTRAINTS = "Zoom links should be of the format www.nus-sg.zoom.us/[path]";
    public static final String ZOOM_LINK_DOMAIN = "https://nus-sg.zoom.us/";
    public static final String ZOOM_LINK_PATH = "[a-zA-Z0-9?=/]+";
    public static final String VALIDATION_REGEX = ZOOM_LINK_DOMAIN + ZOOM_LINK_PATH;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param zoomLink A valid email address.
     */
    public ZoomLink(String zoomLink) {
        requireNonNull(zoomLink);
        checkArgument(isValidZoomLink(zoomLink), MESSAGE_CONSTRAINTS);
        value = zoomLink;
    }

    /**
     * Returns if a given string is a valid email.
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

    /**
     * Returns the link stored in the object.
     * @return String zoom link.
     */
    public String getLink() {
        return this.value;
    }

}
