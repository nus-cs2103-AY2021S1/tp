package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Remark} matches any of the keywords given.
 */
public class RemarkContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public RemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(person.getRemark().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RemarkContainsKeywordsPredicate) other).keywords)); // state check
    }

}
