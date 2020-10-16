package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class ContainsTagPredicate implements Predicate<Person> {
    private final List<String> tagKeywords;

    public ContainsTagPredicate(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        return tagKeywords.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsTagPredicate // instanceof handles nulls
                && tagKeywords.equals(((ContainsTagPredicate) other).tagKeywords)); // state check
    }

}
