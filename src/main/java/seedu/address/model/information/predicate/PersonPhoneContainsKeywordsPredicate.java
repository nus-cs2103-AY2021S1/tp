package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches all of the keywords given.
 */
public class PersonPhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonPhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        assert keywords.stream().allMatch(x -> Double.parseDouble(x) >= 0) : "invalid phone number";
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonPhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonPhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
