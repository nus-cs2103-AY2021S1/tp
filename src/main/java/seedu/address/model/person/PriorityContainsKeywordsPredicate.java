package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Name} matches any of the keyword given.
 */
public class PriorityContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final String keyword;

    public PriorityContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        if (keyword.isBlank()) {
            return false;
        }
        return StringUtil.containsWordIgnoreCase(flashcard.getPriority().toString(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((PriorityContainsKeywordsPredicate) other).keyword)); // state check
    }

}
