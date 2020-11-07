package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches all the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keyword) {
        this.keywords = keyword;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
            .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
