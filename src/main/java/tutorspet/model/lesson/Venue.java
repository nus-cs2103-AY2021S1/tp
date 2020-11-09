package tutorspet.model.lesson;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.util.AppUtil.checkArgument;

/**
 * Represents the Venue of a Lesson.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenue(String)}
 */
public class Venue {

    public static final String SPECIAL_CHARACTERS = "_-.~%:/?#[]@!$&'()*+,;=";
    public static final String MESSAGE_CONSTRAINTS = "Venues should only contain alphanumeric characters, spaces,"
            + " these special characters, excluding the parentheses, (" + SPECIAL_CHARACTERS + ")"
            + " and should not be blank.";
    public static final String VALIDATION_REGEX = "[\\w][\\w-.~%:/?#\\[\\]@!$&'()*+,;= ]*";

    public final String venue;

    /**
     * Constructs a {@code Venue}.
     *
     * @param venue A valid venue.
     */
    public Venue(String venue) {
        requireNonNull(venue);
        checkArgument(isValidVenue(venue), MESSAGE_CONSTRAINTS);

        this.venue = venue;
    }

    /**
     * Returns true if the given String is a valid venue.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Venue // instanceof handles nulls
                && venue.equals(((Venue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }
}
