package seedu.address.model.event;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactContainsTagsPredicate;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class EventContainsTagsPredicate implements Predicate<Event> {
    /**
     * Tags provided by the user to search for contacts containing a matching tag.
     */
    private final Set<Tag> searchTags;
    public EventContainsTagsPredicate(Set<Tag> searchTags) {
        assert !searchTags.isEmpty() : "At least one search tag must be present";
        this.searchTags = searchTags;
    }

    @Override
    public boolean test(Event event) {
        requireNonNull(event);
        boolean isEventTagPresent = !event.getTags().isEmpty();
        if (isEventTagPresent) {
            Set<Tag> taskTags = event.getTags();
            return searchTags.stream()
                    .anyMatch(tag -> taskTags.contains(tag));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsTagsPredicate // instanceof handles nulls
                && searchTags.equals(((EventContainsTagsPredicate) other).searchTags)); // state check
    }
}
