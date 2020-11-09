package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code UrlLink} matches all of the keywords given.
 */
public class PersonUrlLinkContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonUrlLinkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (person.getUrlLinkOptional().isEmpty()) { //person does not have url stored
            return keywords.isEmpty();
        }

        return keywords.stream()
                .allMatch(keyword ->
                        StringUtil.containsPhraseIgnoreCase(person.getUrlLinkOptional().get().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonUrlLinkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonUrlLinkContainsKeywordsPredicate) other).keywords)); // state check
    }

}
