package seedu.bagel.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import seedu.bagel.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(flashcard.getTitle().fullTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
