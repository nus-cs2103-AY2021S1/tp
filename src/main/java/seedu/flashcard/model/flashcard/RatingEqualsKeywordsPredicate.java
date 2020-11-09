package seedu.flashcard.model.flashcard;

import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Rating} is equivalent to any of the ratings given.
 */
public class RatingEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final Rating rating;

    public RatingEqualsKeywordsPredicate(Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        if (rating.equals(null)) {
            // no filter applied
            return true;
        } else {
            return rating.equals(flashcard.getRating());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RatingEqualsKeywordsPredicate // instanceof handles nulls
                && rating == ((RatingEqualsKeywordsPredicate) other).rating); // state check
    }

    public Rating getRating() {
        return this.rating;
    }
}

