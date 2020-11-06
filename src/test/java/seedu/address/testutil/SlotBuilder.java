package seedu.address.testutil;

import java.time.LocalTime;

import seedu.address.model.timetable.Activity;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;

/**
 * A utility class to help with building Slot objects.
 */
public class SlotBuilder {

    public static final Activity DEFAULT_ACTIVITY = Activity.empty();

    public static final Day DEFAULT_DAY = Day.MONDAY;

    public static final Duration DEFAULT_DURATION = new Duration(LocalTime.MIN, LocalTime.of(23, 59));

    private Activity activity;

    private Day day;

    private Duration duration;

    /**
     * Creates a {@code SlotBuilder} with the default details.
     */
    public SlotBuilder() {
        activity = DEFAULT_ACTIVITY;
        day = DEFAULT_DAY;
        duration = DEFAULT_DURATION;
    }

    /**
     * Initializes the SlotBuilder with the data of {@code slotToCopy}.
     */
    public SlotBuilder(Slot slotToCopy) {
        activity = slotToCopy.getActivity();
        day = slotToCopy.getDay();
        duration = slotToCopy.getDuration();
    }

    /**
     * Sets the {@code Activity} of the {@code Slot} that we are building.
     */
    public SlotBuilder withActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Slot} that we are building.
     */
    public SlotBuilder withDay(Day day) {
        this.day = day;
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Slot} that we are building.
     */
    public SlotBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Slot build() {
        return new Slot(activity, day, duration);
    }
}
