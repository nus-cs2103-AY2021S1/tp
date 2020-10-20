package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Office} matches any of the keywords given.
 */
public class OfficeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public OfficeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(person.getOffice().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OfficeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((OfficeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
