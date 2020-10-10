package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the names given
 * and a {@code Person}'s {@code Tags} matches all of the tags given.
 */
public class PersonHasTagsAndNamePredicate implements Predicate<Person> {
    private final List<String> names;
    private final List<String> tags;

    @SuppressWarnings("CheckStyle")
    public PersonHasTagsAndNamePredicate(List<String> names, List<String> tags) {
        this.names = names;
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return names.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
                &&
                tags.stream()
                .allMatch(tag -> person.getTags().contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonHasTagsAndNamePredicate // instanceof handles nulls
                && names.equals(((PersonHasTagsAndNamePredicate) other).names)
                && tags.equals(((PersonHasTagsAndNamePredicate) other).tags)); // state check
    }

}
