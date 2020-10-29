package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ReadOnlyVEvent;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * This class provides the basic functionalities of event operations.
 * Mapping of local Event to jfxtras's VEvent is contained here.
 */
public class Scheduler implements ReadOnlyEvent, ReadOnlyVEvent {

    private final ObservableList<VEvent> internalList = FXCollections.observableArrayList();
    private final ObservableList<VEvent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public Scheduler() {}

    /**
     * Creates a Scheduler using the events in the {@code toBeCopied}
     */
    public Scheduler(ReadOnlyEvent toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Creates a Scheduler using the list of events in the {@code lstOfEvents}
     */
    public Scheduler(List<Event> lstOfEvents) {
        requireNonNull(lstOfEvents);
        resetData(lstOfEvents);
    }

    /**
     * Returns true if {@code event} contains only unique events.
     */
    private boolean vEventsAreUnique(List<VEvent> vEvents) {
        for (int i = 0; i < vEvents.size() - 1; i++) {
            for (int j = i + 1; j < vEvents.size(); j++) {
                if (isSameVEvent(vEvents.get(i), vEvents.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSameVEvent(VEvent e1, VEvent e2) {
        return e1.getSummary().equals(e2.getSummary()) // check name of event
                && e1.getDateTimeStart().equals(e2.getDateTimeStart()) // start time
                && e1.getDateTimeEnd().equals(e2.getDateTimeEnd()); // end time
    }

    private boolean isSameEvent(Event e1, Event e2) {
        return e1.isSameEvent(e2);
    }

    /**
     * Checks if there are events in the current schedule that clashes with the {@code eventToCheck}
     */
    public boolean isClashingEvents(Event eventToCheck) {
        List<Event> events = getEventsList();
        for (int i = 0; i < events.size(); i++) {
            Event currentEvent = events.get(i);
            if (currentEvent.isOverlapping(eventToCheck)) {
                return true;
            }
        }
        return false;
    }

    /** Replaces the contents of this list with {@code vEvents}.
     * {@code vEvents} must not contain duplicate VEvent.
     */
    public void setVEvents(List<VEvent> vEvents) {
        requireAllNonNull(vEvents);
        if (!vEventsAreUnique(vEvents)) {
            throw new DuplicateEventException();
        }
        this.internalList.setAll(vEvents);
    }

    /**
     * Resets the existing data of this {@code Scheduler} with {@code newData}.
     */
    public void resetData(ReadOnlyEvent newData) {
        requireNonNull(newData);
        setVEvents(Mapper.mapListOfEventsToVEvent(newData.getEventsList()));
    }

    /**
     * Resets the existing data of this {@code Scheduler} with {@code newData}.
     */
    public void resetData(List<Event> newData) {
        requireNonNull(newData);
        setVEvents(Mapper.mapListOfEventsToVEvent(newData));
    }

    /**
     * Returns true if a Event with the same identity as {@code eventToCheck} exists in Scheduler.
     */
    public boolean hasEvent(Event eventToCheck) {
        requireNonNull(eventToCheck);
        return internalList.stream().map(Mapper::mapVEventToEvent).anyMatch(Event -> isSameEvent(eventToCheck,
                Event));
    }

    /**
     * Adds a {@code eventToAdd} to Scheduler.
     * The {@code eventToAdd} must not already exist in Scheduler.
     */
    public void addEvent(Event eventToAdd) {
        requireNonNull(eventToAdd);
        if (hasEvent(eventToAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(Mapper.mapEventToVEvent(eventToAdd));
    }

    /**
     * Removes {@code toRemove} from this Scheduler.
     * {@code toRemove} must exist in Scheduler.
     */
    public void removeEvent(Event toRemove) {
        requireNonNull(toRemove);
        Index eventToRemoveIndex = getEventIndex(toRemove).orElseThrow(EventNotFoundException::new);
        internalList.remove(eventToRemoveIndex.getZeroBased());
    }

    private Optional<Index> getEventIndex(Event event) {
        List<Event> events = getEventsList();
        for (int i = 0; i < events.size(); i++) {
            Event currEvent = events.get(i);
            if (currEvent.isSameEvent(event)) {
                return Optional.ofNullable(Index.fromZeroBased(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return internalUnmodifiableList.size() + " Events in Schedule";
    }

    @Override
    public ObservableList<VEvent> getVEvents() {
        return this.internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof Scheduler)) {
            return false;
        }

        Scheduler otherScheduler = (Scheduler) other;
        ObservableList<VEvent> otherList = otherScheduler.getVEvents();
        if (otherList.size() != this.internalList.size()) {
            return false;
        }

        return (new HashSet<>(otherList).equals(new HashSet<>(this.internalList)));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public List<Event> getEventsList() {
        return Mapper.mapListOfVEventsToEvent(this.internalList);
    }

}
