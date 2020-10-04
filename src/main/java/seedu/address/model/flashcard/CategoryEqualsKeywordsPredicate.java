package seedu.address.model.flashcard;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Category} is equivalent to any of the keywords given.
 */
public class CategoryEqualsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public CategoryEqualsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.equalsToCategoryIgnoreCase(flashcard.getCategory().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryEqualsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CategoryEqualsKeywordsPredicate) other).keywords)); // state check
    }
}
