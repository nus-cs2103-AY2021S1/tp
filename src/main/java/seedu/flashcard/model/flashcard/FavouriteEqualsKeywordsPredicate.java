package seedu.flashcard.model.flashcard;

import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code isFavourite} is equivalent to isFavourite boolean given.
 */
public class FavouriteEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final Boolean isFavourite;

    public FavouriteEqualsKeywordsPredicate(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return isFavourite == flashcard.isFavourite();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavouriteEqualsKeywordsPredicate // instanceof handles nulls
                && (isFavourite == ((FavouriteEqualsKeywordsPredicate) other).isFavourite)); // state check
    }

    public Boolean getIsFavourite() {
        return this.isFavourite;
    }
}
