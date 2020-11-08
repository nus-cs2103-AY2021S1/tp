package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Zoom Link for a module in the module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidZoomLink(String)}
 */
public class ZoomLink {

    public static final String MESSAGE_CONSTRAINTS = "Zoom links should belong to the NUS domain"
            + " and adhere to the format: https://nus-sg.zoom.us/[path]\n"
            + "The zoom link path should only contain alphanumeric characters, these "
            + "special characters, excluding the parentheses, (?=/) and should not be blank";
    private static final String ZOOM_LINK_DOMAIN = "https://nus-sg.zoom.us/";
    private static final String ZOOM_LINK_PATH = "[a-zA-Z0-9?=/]+";
    public static final String VALIDATION_REGEX = ZOOM_LINK_DOMAIN + ZOOM_LINK_PATH;

    /** String describing the zoom link. */
    private final String link;

    /**
     * Constructs a {@code ZoomLink}.
     *
     * @param zoomLink A valid zoom link.
     */
    public ZoomLink(String zoomLink) {
        requireNonNull(zoomLink);
        checkArgument(isValidZoomLink(zoomLink), MESSAGE_CONSTRAINTS);
        link = zoomLink;
    }

    /**
     * Determines if a given string is a valid zoom link.
     *
     * @param test A given String to test.
     * @return True if the given string is a valid zoom link.
     */
    public static boolean isValidZoomLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.link;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ZoomLink // instanceof handles nulls
                && link.equals(((ZoomLink) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

}
