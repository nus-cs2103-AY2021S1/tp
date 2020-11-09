package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Email} matches all of the keywords given.
 */
public class PersonEmailContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonEmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(person.getEmail().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonEmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonEmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
