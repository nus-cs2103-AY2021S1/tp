package seedu.flashcard.model.flashcard;

import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Category, Rating, isFavourite, Tags} matches any of the keywords given.
 */
public class MultipleFieldsEqualKeywordsPredicate implements Predicate<Flashcard> {
    private final CategoryEqualsKeywordsPredicate categoryPredicate;
    private final RatingEqualsKeywordsPredicate ratingPredicate;
    private final FavouriteEqualsKeywordsPredicate favouritePredicate;
    private final TagsEqualKeywordsPredicate tagsPredicate;
    private final boolean isCategoryApplied;
    private final boolean isRatingApplied;
    private final boolean isFavouriteApplied;
    private final boolean isTagsApplied;

    /**
     * Creates MultipleFieldsEqualsKeywordsPredicate that contains specific predicates for category, rating
     * favourite and tags.
     */
    public MultipleFieldsEqualKeywordsPredicate(CategoryEqualsKeywordsPredicate categoryPredicate,
                                                RatingEqualsKeywordsPredicate ratingPredicate,
                                                FavouriteEqualsKeywordsPredicate favouritePredicate,
                                                TagsEqualKeywordsPredicate tagsPredicate) {
        this.categoryPredicate = categoryPredicate;
        this.ratingPredicate = ratingPredicate;
        this.favouritePredicate = favouritePredicate;
        this.tagsPredicate = tagsPredicate;
        this.isCategoryApplied = categoryPredicate.getCategory() != null;
        this.isRatingApplied = ratingPredicate.getRating() != null;
        this.isFavouriteApplied = favouritePredicate.getIsFavourite() != null;
        this.isTagsApplied = tagsPredicate.getTags() != null;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        boolean isCategoryMatch = true;
        boolean isRatingMatch = true;
        boolean isFavouriteMatch = true;
        boolean areTagsMatch = true;

        assert flashcard != null;

        if (isCategoryApplied) {
            isCategoryMatch = categoryPredicate.test(flashcard);
        }
        if (isRatingApplied) {
            isRatingMatch = ratingPredicate.test(flashcard);
        }
        if (isFavouriteApplied) {
            isFavouriteMatch = favouritePredicate.test(flashcard);
        }
        if (isTagsApplied) {
            areTagsMatch = tagsPredicate.test(flashcard);
        }
        return isCategoryMatch && isRatingMatch && isFavouriteMatch && areTagsMatch;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqualsToOther = (other instanceof MultipleFieldsEqualKeywordsPredicate // instanceof handles nulls
                && categoryPredicate.equals(((MultipleFieldsEqualKeywordsPredicate) other).categoryPredicate)
                && ratingPredicate.equals(((MultipleFieldsEqualKeywordsPredicate) other).ratingPredicate)
                && favouritePredicate.equals(((MultipleFieldsEqualKeywordsPredicate) other).favouritePredicate)
                && tagsPredicate.equals(((MultipleFieldsEqualKeywordsPredicate) other).tagsPredicate));

        return other == this // short circuit if same object
                || isEqualsToOther;
    }

}
