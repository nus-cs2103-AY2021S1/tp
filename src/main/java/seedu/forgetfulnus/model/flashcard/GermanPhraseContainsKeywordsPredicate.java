package seedu.forgetfulnus.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import seedu.forgetfulnus.commons.util.StringUtil;

/**
 * Tests that a {@code FlashCard}'s {@code Name} matches any of the keywords given.
 */
public class GermanPhraseContainsKeywordsPredicate implements Predicate<FlashCard> {
    private final List<String> keywords;

    public GermanPhraseContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(FlashCard flashCard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(flashCard.getGermanPhrase().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GermanPhraseContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GermanPhraseContainsKeywordsPredicate) other).keywords)); // state check
    }
}
