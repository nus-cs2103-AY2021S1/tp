package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

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
        List<String> tags = person.getTags().stream()
                .map(x -> x.tagName.toLowerCase())
                .collect(Collectors.toList());
        return tagKeywords.stream()
                .anyMatch(keyword -> tags.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((TagContainsKeywordsPredicate) other).tagKeywords)); // state check
    }

}
