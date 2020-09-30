package seedu.address.flashcard;

import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.List;
import java.util.function.Predicate;

public class FlashcardContainsTagPredicate implements Predicate<Flashcard> {

    private final List<String> keywords;

    public FlashcardContainsTagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> flashcard.matchTag(new Tag(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardContainsTagPredicate // instanceof handles nulls
                && keywords.equals(((FlashcardContainsTagPredicate) other).keywords)); // state check
    }
}
