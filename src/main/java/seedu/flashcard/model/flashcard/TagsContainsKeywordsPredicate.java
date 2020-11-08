package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.flashcard.model.tag.Tag;

/**
 * Tests that a {@code Flashcard}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        Set<Tag> flashcardTags = flashcard.getTags();

        if (flashcardTags.isEmpty()) {
            return false;
        }

        return keywords.stream()
                .anyMatch(keyword ->
                        flashcardTags.stream()
                                .map(tag -> tag.toString().toLowerCase()) //Convert tag to string
                                .anyMatch(tag -> tag.contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
