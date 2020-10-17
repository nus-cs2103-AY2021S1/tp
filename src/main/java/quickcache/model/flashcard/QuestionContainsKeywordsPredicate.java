package quickcache.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import quickcache.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcards}'s {@code Question} matches any of the keywords given.
 */
public class QuestionContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public QuestionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordAsSubsetIgnoreCase(flashcard.getQuestion().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuestionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QuestionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
