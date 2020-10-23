package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Category} matches any of the keywords given.
 */
public class CategoryContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public CategoryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword ->
                        flashcard.getCategory().toString().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CategoryContainsKeywordsPredicate) other).keywords)); // state check
    }
}
