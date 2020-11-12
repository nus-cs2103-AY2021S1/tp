package seedu.address.model.timetable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

public class Timetable {

    private final UniqueSlotList slots;

    {
        slots = new UniqueSlotList();
    }

    /**
     * Returns true if this Slot is already in use in the Timetable.
     * @param slot The slot to be checked.
     * @return true if this Slot is already in use in the Timetable.
     */
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return slots.contains(slot);
    }

    /**
     * Returns true if this slot has overlapping duration with the other slots in timetable.
     * @param slot The slot to be checked.
     * @return true if this slot has overlapping duration with the other slots in timetable.
     */
    public boolean hasOverlapDuration(Slot slot) {
        requireNonNull(slot);
        return slots.hasOverlapDuration(slot);
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public void deleteSlot(Slot slot) {
        slots.remove(slot);
    }

    public void deleteSlot(Activity activity) {
        slots.remove(activity);
    }

    public void setSlot(Activity target, Activity editedActivity) {
        slots.setSlot(target, editedActivity);
    }

    public void setSlots(List<Slot> slots) {
        this.slots.setSlots(slots);
    }

    public ObservableList<Slot> getSlotList() {
        return slots.asUnmodifiableObservableList();
    }
}
