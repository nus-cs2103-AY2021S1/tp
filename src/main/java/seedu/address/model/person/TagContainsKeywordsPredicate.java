package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Name} matches any of the keyword given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final String keyword;

    public TagContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        if (keyword.isBlank()) {
            return false;
        }
        return flashcard.getTags().stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(keyword, tag.tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((TagContainsKeywordsPredicate) other).keyword)); // state check
    }

}
