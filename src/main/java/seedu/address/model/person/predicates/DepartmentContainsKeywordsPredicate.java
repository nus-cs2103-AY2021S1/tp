package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Department} matches any of the keywords given.
 */
public class DepartmentContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public DepartmentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(person.getDepartment().value,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DepartmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DepartmentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
