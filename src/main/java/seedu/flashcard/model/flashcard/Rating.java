package seedu.flashcard.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.commons.util.AppUtil.checkArgument;

/**
 * Represents the Rating on the Flashcard.
 */
public class Rating {
    public static final String MESSAGE_CONSTRAINTS = "Star rating can take any value from 1-5, and can be blank";
    /*
     * The rating must either be an empty string or take a string value between 1-5 inclusive.
     */
    public static final String VALIDATION_REGEX = "^$|\\b[1-5]\\b";
    private String rating;
    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        assert rating.equals("") || (Integer.valueOf(rating) <= 5 && Integer.valueOf(rating) >= 1);
        this.rating = rating;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return rating;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && rating.equals(((Rating) other).rating)); // state check
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }
}
