package quickcache.model.flashcard;

import java.util.Set;
import java.util.function.Predicate;

public class FlashcardContainsTagPredicate implements Predicate<Flashcard> {

    private final Set<Tag> tagsToMatch;

    public FlashcardContainsTagPredicate(Set<Tag> tagsToMatch) {
        this.tagsToMatch = tagsToMatch;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return flashcard.containsAllTags(tagsToMatch);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardContainsTagPredicate // instanceof handles nulls
                && tagsToMatch.equals(((FlashcardContainsTagPredicate) other).tagsToMatch)); // state check
    }
}
