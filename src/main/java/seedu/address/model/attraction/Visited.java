package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Attraction's visited status in TrackPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidVisited(String)} (String)}
 */
public class Visited {

    public static final String MESSAGE_CONSTRAINTS = "Visited should be either "
            + "TRUE or FALSE.";
    public static final String VALIDATION_REGEX = "(?i)\\bTRUE\\b|\\bFALSE\\b|^$";

    public final String value;

    /**
     * Constructs a {@code Visited}.
     *
     * @param visited A valid visited status.
     */
    public Visited(String visited) {
        requireNonNull(visited);
        checkArgument(isValidVisited(visited), MESSAGE_CONSTRAINTS);
        value = visited.toUpperCase();
    }

    /**
     * Constructs a {@code Visited} without value.
     */
    public Visited() {
        value = "";
    }

    /**
     * Returns if a given string is a valid visited status.
     */
    public static boolean isValidVisited(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value.equalsIgnoreCase("TRUE")) {
            return "Visited";
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Visited // instanceof handles nulls
                && value.equals(((Visited) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
