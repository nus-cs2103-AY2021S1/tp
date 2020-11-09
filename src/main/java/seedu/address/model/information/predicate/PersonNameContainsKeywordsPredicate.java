package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the keywords given.
 */
public class PersonNameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                // Name contains keyword
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
