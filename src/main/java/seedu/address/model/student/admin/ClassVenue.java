package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's class venue in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassVenue(String)}
 */
public class ClassVenue {

    public static final String MESSAGE_CONSTRAINTS = "Class Venues can take any values, and it should not be blank";

    /*
     * The first character of the class venue must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String venue;

    /**
     * Constructs an {@code ClassVenue}.
     *
     * @param classVenue A valid classVenue.
     */
    public ClassVenue(String classVenue) {
        requireNonNull(classVenue);
        checkArgument(isValidClassVenue(classVenue), MESSAGE_CONSTRAINTS);
        venue = classVenue;
    }

    /**
     * Returns true if a given string is a valid classVenue.
     */
    public static boolean isValidClassVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassVenue // instanceof handles nulls
                && venue.equals(((ClassVenue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }

}
