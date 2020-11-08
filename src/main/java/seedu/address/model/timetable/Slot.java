package seedu.address.model.timetable;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Slot implements Comparable<Slot> {

    private final Activity activity;

    private final Day day;

    private final Duration duration;

    /**
     * Constructs a new Slot object with an empty Activity. This is used to identify the slot to delete in timetable.
     * @param day The day of the slot.
     * @param duration The timeslot.
     */
    public Slot(Day day, Duration duration) {
        activity = Activity.empty();
        this.day = day;
        this.duration = duration;
    }

    /**
     * Constructs a new Slot object.
     * @param activity The activity to be added to timetable.
     * @param day The day to add to.
     * @param duration The duration to input in the timetable.
     */
    public Slot(Activity activity, Day day, Duration duration) {
        requireAllNonNull(activity, day, duration);
        this.activity = activity;
        this.day = day;
        this.duration = duration;
    }

    public Activity getActivity() {
        return activity;
    }

    public Day getDay() {
        return day;
    }

    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns true if this slot has overlapping duration with the other slot.
     * @param otherSlot The other slot to be checked.
     * @return true if this slot has overlapping duration with the other slot.
     */
    public boolean hasOverlapDuration(Slot otherSlot) {
        return day.isSameDay(otherSlot.getDay())
                && duration.hasOverlapDuration(otherSlot.getDuration());
    }

    /**
     * Returns true if both slots have the same day and duration.
     * This defines a weaker notion of equality between two slots.
     */
    public boolean isSameSlot(Slot otherSlot) {
        if (otherSlot == this) {
            return true;
        }

        return otherSlot != null
                && otherSlot.getDay().isSameDay(day)
                && otherSlot.getDuration().isSameDuration(duration);
    }

    /**
     * Returns true if both slots have the same activity, day and duration.
     * This defines a stronger notion of equality between two slots.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Slot)) {
            return false;
        }

        Slot otherSlot = (Slot) other;
        return otherSlot.getActivity().equals(activity)
                && otherSlot.getDay().equals(day)
                && otherSlot.getDuration().isSameDuration(duration);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(activity, day, duration);
    }

    @Override
    public String toString() {
        return activity.getName() + " on " + day.toString() + " " + duration.toString();
    }

    @Override
    public int compareTo(Slot o) {
        return this.getDuration().getStartTime().compareTo(o.getDuration().getStartTime());
    }
}
