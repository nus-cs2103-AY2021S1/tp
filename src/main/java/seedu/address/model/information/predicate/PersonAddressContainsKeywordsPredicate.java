package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Address} matches all of the keywords given.
 */
public class PersonAddressContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonAddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                // Address contains keyword
                .allMatch(keyword -> person.getAddressOptional()
                        .filter(address -> StringUtil.containsPhraseIgnoreCase(address.value, keyword))
                        .isPresent());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonAddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonAddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}
