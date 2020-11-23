package seedu.tr4cker.model.countdown;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tr4cker.model.countdown.exceptions.DuplicateEventException;
import seedu.tr4cker.model.countdown.exceptions.EventNotFoundException;

/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * An event is considered unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and
 * updating of events uses Event#isSameEvent(Event) for equality so as to ensure that the event being added
 * or updated is unique in terms of identity in the UniqueEventList. However, the removal of a event uses
 * n#equals(Object) so as to ensure that the event with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with another {@code UniqueEventList}.
     */
    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    /**
     * Sorts the the list according to date, starting from the earliest upcoming event.
     * Events which have passed will be at the back of the list in no particular order.
     */
    public void sortEventsByDate() {
        LocalDate now = LocalDate.now();
        Collections.sort(internalList, (event1, event2) -> {
            try {
                return event1.getEventDate().getDate().isBefore(now) && event2.getEventDate().getDate().isBefore(now)
                        ? 0 : event1.getEventDate().getDate().isBefore(now)
                        ? 1 : event2.getEventDate().getDate().isBefore(now)
                        ? -1 : event1.getEventDate().getDate().compareTo(event2.getEventDate().getDate());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.tr4cker.model.countdown.UniqueEventList // instanceof handles nulls
                && internalList.equals(((seedu.tr4cker.model.countdown.UniqueEventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the first upcoming event in the sorted list of events.
     *
     * @return Earliest upcoming event.
     */
    public Event firstEvent() {
        if (internalUnmodifiableList.size() < 1) {
            return null;
        }
        sortEventsByDate();
        return internalUnmodifiableList.get(0);
    }

    /**
     * Gets the second upcoming event in the sorted list of events.
     *
     * @return Second earliest upcoming event.
     */
    public Event secondEvent() {
        if (internalUnmodifiableList.size() < 2) {
            return null;
        }
        sortEventsByDate();
        return internalUnmodifiableList.get(1);
    }
}
