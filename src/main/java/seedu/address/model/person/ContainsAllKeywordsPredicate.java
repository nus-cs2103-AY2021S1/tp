package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Name} matches any of the keywords given.
 */
public class ContainsAllKeywordsPredicate implements Predicate<Flashcard> {
    private final List<Predicate<Flashcard>> predicates;

    public ContainsAllKeywordsPredicate(List<Predicate<Flashcard>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return predicates.stream()
                .allMatch(predicate -> predicate.test(flashcard));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsAllKeywordsPredicate // instanceof handles nulls
                && predicates.equals(((ContainsAllKeywordsPredicate) other).predicates)); // state check
    }

}
