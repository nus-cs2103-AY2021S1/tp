package jimmy.mcgymmy.model.predicate;

import java.util.List;
import java.util.function.Predicate;

import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.tag.Tag;

/**
 * Tests that a {@code Food}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Food> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Food food) {
        return keywords.stream()
                .anyMatch(keyword -> {
                    try {
                        return food.getTags().contains(new Tag(keyword));
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
