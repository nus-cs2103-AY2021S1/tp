package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import seedu.flashcard.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Question} matches any of the keywords given.
 */
public class QuestionContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public QuestionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Formats the question for QuestionContainsKeywordsPredicate by adding whitespace between special characters.
     * @param question Question to format.
     * @return Returns a formatted question.
     */
    public String formatQuestionForQuestionContainsKeywordsPredicate(Question question) {
        return question.toString().replaceAll("[^a-zA-Z0-9]", " $0 ");
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                            formatQuestionForQuestionContainsKeywordsPredicate(flashcard.getQuestion()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuestionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QuestionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
