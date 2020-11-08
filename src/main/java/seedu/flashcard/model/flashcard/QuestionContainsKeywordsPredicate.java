package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Flashcard}'s {@code Question} matches any of the keywords given.
 */
public class QuestionContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public QuestionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        String flashcardQuestion = flashcard.getQuestion().toString().toLowerCase();
        return keywords.stream()
                .anyMatch(keyword ->
                        flashcardQuestion.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuestionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QuestionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
