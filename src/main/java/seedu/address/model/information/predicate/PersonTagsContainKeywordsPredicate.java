package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that any of a {@code Person}'s {@code Tags} matches all of the keywords given.
 */
public class PersonTagsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonTagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                // Any tags contain keyword
                .allMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsPhraseIgnoreCase(tag.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonTagsContainKeywordsPredicate) other).keywords)); // state check
    }
}
