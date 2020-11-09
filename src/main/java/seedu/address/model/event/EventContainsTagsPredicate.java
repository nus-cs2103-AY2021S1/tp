package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Represents a predicate that returns true for the given tag.
 */
public class EventContainsTagsPredicate implements Predicate<Event> {
    /**
     * Tags provided by the user to search for events containing a matching tag.
     */
    private final Set<Tag> searchTags;

    /**
     * Represents the Predicate for Tags.
     * @param searchTags the tags to search for.
     */
    public EventContainsTagsPredicate(Set<Tag> searchTags) {
        assert !searchTags.isEmpty() : "At least one search tag must be present";
        this.searchTags = searchTags;
    }

    @Override
    public boolean test(Event event) {
        requireNonNull(event);
        boolean isEventTagPresent = !event.getTags().isEmpty();
        if (isEventTagPresent) {
            Set<Tag> eventTags = event.getTags();
            return searchTags.stream()
                    .anyMatch(tag -> eventTags.contains(tag));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof EventContainsTagsPredicate) {
            return this.searchTags.equals(((EventContainsTagsPredicate) other).searchTags);
        } else {
            return false;
        }
    }
}
