package seedu.flashcard.model.flashcard;

import java.util.function.Predicate;

public class MultipleFieldsEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final CategoryEqualsKeywordsPredicate categoryPredicate;
    private final RatingEqualsKeywordsPredicate ratingPredicate;
    private final FavouriteEqualsKeywordsPredicate favouritePredicate;
    private final boolean isCategoryApplied;
    private final boolean isRatingApplied;
    private final boolean isFavouriteApplied;

    public MultipleFieldsEqualsKeywordsPredicate(CategoryEqualsKeywordsPredicate categoryPredicate,
                                                 RatingEqualsKeywordsPredicate ratingPredicate,
                                                 FavouriteEqualsKeywordsPredicate favouritePredicate) {
        this.categoryPredicate = categoryPredicate;
        this.ratingPredicate = ratingPredicate;
        this.favouritePredicate = favouritePredicate;
        this.isCategoryApplied = categoryPredicate.getCategory() != null;
        this.isRatingApplied = ratingPredicate.getRating() != null;
        this.isFavouriteApplied = favouritePredicate.getIsFavourite() != null;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        boolean categoryOutcome;
        boolean ratingOutcome;
        boolean favouriteOutcome;
        if (isCategoryApplied) {
            categoryOutcome = categoryPredicate.getCategory().equals(flashcard.getCategory());
        } else {
            categoryOutcome = true;
        }
        if (isRatingApplied) {
            ratingOutcome = ratingPredicate.getRating().equals(flashcard.getRating());
        } else {
            ratingOutcome = true;
        }
        if (isFavouriteApplied) {
            favouriteOutcome = favouritePredicate.getIsFavourite().equals(flashcard.isFavourite());
        } else {
            favouriteOutcome = true;
        }
        return categoryOutcome && ratingOutcome && favouriteOutcome;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MultipleFieldsEqualsKeywordsPredicate // instanceof handles nulls
                && categoryPredicate.equals(((MultipleFieldsEqualsKeywordsPredicate) other).categoryPredicate)
                && ratingPredicate.equals(((MultipleFieldsEqualsKeywordsPredicate) other).ratingPredicate)
                && favouritePredicate.equals(((MultipleFieldsEqualsKeywordsPredicate) other).favouritePredicate));
    }

}
