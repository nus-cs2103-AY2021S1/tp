package seedu.address.model.person.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> tagKeywords;

    public TagContainsKeywordsPredicate(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        if (tagKeywords.isEmpty()) {
            return false;
        }

        return tagKeywords.stream()
                .allMatch(keyword -> anyTagsContainsKeyword(person.getTags(), keyword));
    }

    /**
     * Returns true if the {@code keyword} is contained in any of the tags.
     */
    private static boolean anyTagsContainsKeyword(Set<Tag> tagSet, String keyword) {
        return tagSet.stream().anyMatch(tag -> StringUtil.containsSubWordOrWordIgnoreCase(tag.tagName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((TagContainsKeywordsPredicate) other).tagKeywords)); // state check
    }

}
