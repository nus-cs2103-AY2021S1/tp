package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Answer} matches any of the keywords given.
 */
public class AnswerContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public AnswerContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        String flashcardAnswer = flashcard.getAnswer().toString().toLowerCase();

        return keywords.stream()
                .anyMatch(keyword ->
                        flashcardAnswer.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnswerContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnswerContainsKeywordsPredicate) other).keywords)); // state check
    }
}
