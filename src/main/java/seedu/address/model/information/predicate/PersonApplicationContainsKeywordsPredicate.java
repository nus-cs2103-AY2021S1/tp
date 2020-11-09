package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Date Of Application} matches all of the keywords given.
 */
public class PersonApplicationContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonApplicationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                // Date of Application contains keyword
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(
                        person.getDateOfApplication().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonApplicationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonApplicationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
