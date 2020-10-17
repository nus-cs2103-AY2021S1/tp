package quickcache.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

/**
 * Compiles all predicates for testing {@code Flashcard}.
 */
public class FlashcardPredicate implements Predicate<Flashcard> {

    private final List<Predicate<Flashcard>> predicates;
    private final Predicate<Flashcard> predicate;

    /**
     * Creates an an instance of a {@code FLashcardPredicate}.
     *
     * @param predicates a list of predicates for testing flashcard.
     */
    public FlashcardPredicate(List<Predicate<Flashcard>> predicates) {
        this.predicates = predicates;
        this.predicate = predicates.stream().reduce(Predicate::and).get();
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return predicate.test(flashcard);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardPredicate // instanceof handles nulls
                && predicates.equals(((FlashcardPredicate) other).predicates)); // state check
    }
}
