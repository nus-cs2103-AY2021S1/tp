package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final String keyword;

    public NameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        if (keyword.isBlank()) {
            return false;
        }
        List<String> words = Arrays.asList(keyword.split("\\s+"));
        return words.stream()
                .allMatch(word -> StringUtil.containsWordIgnoreCase(flashcard.getTitle().fullTitle, word));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsKeywordsPredicate) other).keyword)); // state check
    }

}
