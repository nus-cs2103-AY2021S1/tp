package seedu.address.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Category} is equivalent to any of the categories given.
 */
public class CategoryEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<Category> categoryList;

    public CategoryEqualsKeywordsPredicate(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return categoryList.stream().anyMatch(category -> category.equals(flashcard.getCategory()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryEqualsKeywordsPredicate // instanceof handles nulls
                && categoryList.equals(((CategoryEqualsKeywordsPredicate) other).categoryList)); // state check
    }
}
