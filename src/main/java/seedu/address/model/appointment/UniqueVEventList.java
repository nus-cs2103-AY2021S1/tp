package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.VEventUtil.isSameVEvent;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.model.appointment.exceptions.DuplicateVEventException;

/**
 * A list of vEvents that provides functionality to map from appointments to vEvents.
 * VEvents with the same patientName, startTime and endTime are not allowed.
 * <p>
 * Supports a minimal set of list operations.
 */
public class UniqueVEventList implements Iterable<VEvent> {

    private final ObservableList<VEvent> vEvents = FXCollections.observableArrayList();
    private final ObservableList<VEvent> vEventsUnmodifiableList =
            FXCollections.unmodifiableObservableList(vEvents);

    /**
     * Returns true if the list contains an equivalent vEvent as the given argument.
     */
    public boolean contains(VEvent toCheck) {
        requireNonNull(toCheck);
        return vEvents.stream().anyMatch(vEvent -> isSameVEvent(vEvent, toCheck));
    }

    /**
     * Add a new vEvent to the vEvents list.
     *
     * @param vEvent to add to the list.
     */
    public void addVEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        if (contains(vEvent)) {
            throw new DuplicateVEventException();
        }
        vEvents.add(vEvent);
    }

    /**
     * Deletes the vEvent at the specified index in the list.
     *
     * @param index of the vEvent in the list.
     * @return VEvent object.
     */
    public VEvent deleteVEvent(Index index) {
        requireNonNull(index);
        return vEvents.remove(index.getZeroBased());
    }

    // /**
    //  * Get all appointments backed by vEvents observable list.
    //  */
    // public List<Appointment> getAllAppointments() {
    //     return VEventUtil.vEventsToAppsMapper(this.vEvents);
    // }

    /**
     * Returns the VEvent object.
     *
     * @param index of the question in the list.
     * @return VEvent object.
     */
    public VEvent getVEvent(Index index) {
        requireNonNull(index);
        return vEvents.get(index.getZeroBased());
    }

    /**
     * Sets the vEvent object at the specified index in the list. This should not result in a duplicate
     * vEvent
     *
     * @param index  of the vEvent in the list.
     * @param vEvent object.
     */
    public void setVEvent(Index index, VEvent vEvent) {
        VEvent targetVEvent = vEvents.get(index.getZeroBased());
        if (!isSameVEvent(targetVEvent, vEvent) && contains(vEvent)) {
            throw new DuplicateVEventException();
        }
        vEvents.set(index.getZeroBased(), vEvent);
    }

    public void setVEvents(UniqueVEventList replacement) {
        requireNonNull(replacement);
        vEvents.setAll(replacement.vEvents);
    }

    /**
     * Replaces the contents of this list with {@code vEvents}.
     * vEvents must be unique.
     */
    public void setVEvents(List<VEvent> vEvents) {
        requireAllNonNull(vEvents);
        if (!vEventsAreUnique(vEvents)) {
            throw new DuplicateVEventException();
        }
        this.vEvents.setAll(vEvents);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<VEvent> asUnmodifiableObservableList() {
        return vEventsUnmodifiableList;
    }

    @Override
    public Iterator<VEvent> iterator() {
        return vEvents.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueVEventList // instanceof handles nulls
                && vEvents.equals(((UniqueVEventList) other).vEvents));
    }

    @Override
    public int hashCode() {
        return vEvents.hashCode();
    }

    /**
     * Validates if all events in the list are unique
     *
     * @param vEventList list to be checked
     * @return true if all events are unique
     */
    private boolean vEventsAreUnique(List<VEvent> vEventList) {
        for (int i = 0; i < vEventList.size() - 1; i++) {
            for (int j = i + 1; j < vEventList.size(); j++) {
                if (isSameVEvent(vEventList.get(i), vEventList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
