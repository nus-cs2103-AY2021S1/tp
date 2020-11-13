package seedu.address.model.person.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameDescriptionContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getName().fullName.replaceAll("\\W", " "), keyword)
                        || StringUtil.containsWordIgnoreCase(
                                person.getDescription().value.orElse("").replaceAll("\\W", " "), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
