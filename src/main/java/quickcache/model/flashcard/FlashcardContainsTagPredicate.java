package quickcache.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

public class FlashcardContainsTagPredicate implements Predicate<Flashcard> {

    private final List<Tag> tagsToMatch;

    public FlashcardContainsTagPredicate(List<Tag> tagsToMatch) {
        this.tagsToMatch = tagsToMatch;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return tagsToMatch.stream()
                .anyMatch(flashcard::matchTag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardContainsTagPredicate // instanceof handles nulls
                && tagsToMatch.equals(((FlashcardContainsTagPredicate) other).tagsToMatch)); // state check
    }
}
