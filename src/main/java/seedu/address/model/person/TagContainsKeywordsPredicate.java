package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given and the person is not archived.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return ((!person.getArchiveStatus().archiveStatus)
                && keywords.stream().anyMatch(keyword
                    -> StringUtil
                        .containsWordIgnoreCase(person.getTags().toString()
                                .replaceAll("\\[", "")
                                .replaceAll("]", "")
                                .replaceAll(",", ""), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
