package seedu.address.model.person.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

public class EmailContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
            .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(person.getEmail().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return Arrays.toString(keywords.toArray());
    }
}
