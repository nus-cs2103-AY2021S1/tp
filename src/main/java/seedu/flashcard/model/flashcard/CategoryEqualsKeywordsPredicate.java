package seedu.flashcard.model.flashcard;

import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Category} is equivalent to any of the categories given.
 */
public class CategoryEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final Category category;

    public CategoryEqualsKeywordsPredicate(Category category) {
        this.category = category;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return category.equals(flashcard.getCategory());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryEqualsKeywordsPredicate // instanceof handles nulls
                && category.equals(((CategoryEqualsKeywordsPredicate) other).category)); // state check
    }

    public Category getCategory() {
        return this.category;
    }
}
