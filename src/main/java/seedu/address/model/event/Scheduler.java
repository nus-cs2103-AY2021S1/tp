package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ReadOnlyVEvent;
import seedu.address.model.event.exceptions.DuplicateEventException;

public class Scheduler implements ReadOnlyEvent, ReadOnlyVEvent, Iterable<VEvent> {

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
                && e1.getDateTimeStart().equals(e2.getDateTimeStart())
                && e1.getDateTimeEnd().equals(e2.getDateTimeEnd());
    }

    public List<Event> getListOfEvents() {
        return Mapper.mapListOfVEventsToEvent(this.internalList);
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
     * Returns true if a vEvent with the same identity as {@code vEvent} exists in Scheduler.
     */
    public boolean hasVEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        return internalList.stream().anyMatch(Event -> isSameVEvent(vEvent, Event));
    }

    /**
     * Adds a vEvent to Scheduler.
     * The vEvent must not already exist in Scheduler.
     */
    public void addVEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        if (hasVEvent(vEvent)) {
            throw new DuplicateEventException();
        }
        internalList.add(vEvent);
    }

    /**
     * Removes {@code vEvent} from this {@code Scheduler}.
     * {@code vEvent} must exist in Scheduler.
     */
    public void removeVEvent(Index index) {
        requireNonNull(index);
        internalList.remove(index.getZeroBased());
    }

    /**
     * Returns a VEvent object from the scheduler
     * @param index
     * @return
     */
    public VEvent getVEvent(Index index) {
        requireNonNull(index);
        return internalList.get(index.getZeroBased());
    }

    @Override
    public String toString() {
        return internalUnmodifiableList.size() + " vEvents";
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
        ObservableList<VEvent> otherList =
                otherScheduler.getVEvents();
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
    public Iterator<VEvent> iterator() {
        return internalList.iterator();
    }

    @Override
    public List<Event> getEventsList() {
        return Mapper.mapListOfVEventsToEvent(this.internalList);
    }
}
