package seedu.flashcard.model.flashcard;

import java.util.Set;
import java.util.function.Predicate;

import seedu.flashcard.model.tag.Tag;

/**
 * Tests that a {@code Flashcard}'s {@code Tags} matches any of the keywords given.
 */
public class TagsEqualKeywordsPredicate implements Predicate<Flashcard> {
    private final Set<Tag> tags;

    public TagsEqualKeywordsPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return flashcard.getTags().containsAll(tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsEqualKeywordsPredicate // instanceof handles nulls
                && tags.equals(((TagsEqualKeywordsPredicate) other).tags)); // state check
    }

    public Set<Tag> getTags() {
        return this.tags;
    }
}
