package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Contact}'s {@code Set<Tag>} matches any of the search tags provided by the user.
 */
public class ContactContainsTagsPredicate implements Predicate<Contact> {

    /**
     * Tags provided by the user to search for contacts containing a matching tag.
     */
    private final Set<Tag> tags;

    /**
     * Creates and initialises a new ContactContainsTagsPredicate to test for matching contacts.
     *
     * @param tags Set of tags provided by the user.
     */
    public ContactContainsTagsPredicate(Set<Tag> tags) {
        assert !tags.isEmpty() : "At least one search tag must be present";
        this.tags = tags;
    }

    @Override
    public boolean test(Contact contact) {
        requireNonNull(contact);
        boolean isContactTagPresent = !contact.getTags().isEmpty();
        if (isContactTagPresent) {
            Set<Tag> taskTags = contact.getTags();
            return tags.stream()
                    .anyMatch(taskTags::contains);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsTagsPredicate // instanceof handles nulls
                && tags.equals(((ContactContainsTagsPredicate) other).tags)); // state check
    }
}
