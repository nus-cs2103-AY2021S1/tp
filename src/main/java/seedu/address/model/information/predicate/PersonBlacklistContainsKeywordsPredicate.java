package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Blacklist Status} matches all of the keywords given.
 */
public class PersonBlacklistContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonBlacklistContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                // Blacklist Status matches keyword
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(
                        person.getBlacklistStatus().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonBlacklistContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonBlacklistContainsKeywordsPredicate) other).keywords)); // state check
    }

}
