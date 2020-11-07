package seedu.simplykitchen.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.simplykitchen.model.food.Food;

/**
 * Tests that a {@code Food}'s {@code Description} matches any of the keywords given.
 */
public class TagSearchPredicate implements Predicate<Food> {
    private final Set<Tag> tagsToFind;

    /**
     * @param tagsToFind The set of tags to search for.
     */
    public TagSearchPredicate(Set<Tag> tagsToFind) {
        assert tagsToFind != null : "Tags to find cannot be null.";
        this.tagsToFind = tagsToFind;
    }

    @Override
    public boolean test(Food food) {
        return tagsToFind.stream()
                .anyMatch(tagToFind -> {
                    for (Tag tag : food.getTags()) {
                        if (tag.tagName.equalsIgnoreCase(tagToFind.tagName)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagSearchPredicate // instanceof handles nulls
                && tagsToFind.equals(((TagSearchPredicate) other).tagsToFind)); // state check
    }

}
