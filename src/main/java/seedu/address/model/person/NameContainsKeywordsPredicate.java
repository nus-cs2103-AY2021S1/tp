package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

<<<<<<< Updated upstream:src/main/java/seedu/address/model/person/NameContainsKeywordsPredicate.java
import seedu.address.commons.util.StringUtil;
=======
import jimmy.mcgymmy.commons.util.StringUtil;
import jimmy.mcgymmy.model.food.Food;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/person/NameContainsKeywordsPredicate.java

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Food> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Food food) {
        return keywords.stream()
<<<<<<< Updated upstream:src/main/java/seedu/address/model/person/NameContainsKeywordsPredicate.java
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
=======
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(food.getName(), keyword));
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/person/NameContainsKeywordsPredicate.java
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
