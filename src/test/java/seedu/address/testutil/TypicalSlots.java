package seedu.address.testutil;

import static seedu.address.model.timetable.Day.FRIDAY;
import static seedu.address.model.timetable.Day.MONDAY;
import static seedu.address.model.timetable.Day.THURSDAY;
import static seedu.address.model.timetable.Day.TUESDAY;
import static seedu.address.model.timetable.Day.WEDNESDAY;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1700;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;
import static seedu.address.model.timetable.DurationTest.DURATION_1630_1730;
import static seedu.address.model.timetable.DurationTest.DURATION_1700_1800;
import static seedu.address.model.timetable.DurationTest.DURATION_1800_2000;
import static seedu.address.testutil.TypicalLessons.MA1101R;
import static seedu.address.testutil.TypicalLessons.MA1521;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FitNus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.routine.Routine;
import seedu.address.model.timetable.Activity;
import seedu.address.model.timetable.Slot;

/**
 * A utility class containing a list of {@code Slot} objects to be used in tests.
 */
public class TypicalSlots {

    public static final Slot EMPTY_MONDAY_1630_1730 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(MONDAY).withDuration(DURATION_1630_1730).build();
    public static final Slot EMPTY_MONDAY_1600_1700 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(MONDAY).withDuration(DURATION_1600_1700).build();
    public static final Slot EMPTY_MONDAY_1700_1800 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(MONDAY).withDuration(DURATION_1700_1800).build();
    public static final Slot EMPTY_MONDAY_1600_1800 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(MONDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot EMPTY_TUESDAY_1600_1800 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(TUESDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot EMPTY_TUESDAY_1630_1730 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(TUESDAY).withDuration(DURATION_1630_1730).build();
    public static final Slot EMPTY_TUESDAY_1800_2000 = new SlotBuilder().withActivity(Activity.empty())
            .withDay(TUESDAY).withDuration(DURATION_1800_2000).build();

    public static final Slot LEG_DAY_WEDNESDAY_1600_1800 = new SlotBuilder().withActivity(LEG_DAY)
            .withDay(WEDNESDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot LEG_DAY_WEDNESDAY_1630_1730 = new SlotBuilder().withActivity(LEG_DAY)
            .withDay(WEDNESDAY).withDuration(DURATION_1630_1730).build();
    public static final Slot UPPER_BODY_WEDNESDAY_1600_1800 = new SlotBuilder().withActivity(UPPER_BODY)
            .withDay(WEDNESDAY).withDuration(DURATION_1600_1800).build();

    public static final Slot MA1101R_WEDNESDAY_1600_1800 = new SlotBuilder().withActivity(MA1101R)
            .withDay(WEDNESDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot MA1101R_THURSDAY_1600_1800 = new SlotBuilder().withActivity(MA1101R)
            .withDay(THURSDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot MA1521_FRIDAY_1600_1800 = new SlotBuilder().withActivity(MA1521)
            .withDay(FRIDAY).withDuration(DURATION_1600_1800).build();
    public static final Slot MA1521_FRIDAY_1800_2000 = new SlotBuilder().withActivity(MA1521)
            .withDay(FRIDAY).withDuration(DURATION_1800_2000).build();

    private TypicalSlots() {} // prevents instantiation

    /**
     * Returns an {@code FitNus} with all the typical slots.
     */
    public static FitNus getTypicalFitNus() {
        FitNus fn = new FitNus();
        for (Slot slot : getTypicalSlots()) {
            if (slot.getActivity().isRoutine()) {
                fn.addRoutine((Routine) slot.getActivity());
            } else {
                fn.addLesson((Lesson) slot.getActivity());
            }
            fn.addSlotToTimetable(slot);
        }
        return fn;
    }

    public static List<Slot> getTypicalSlots() {
        return new ArrayList<>(Arrays.asList(
                LEG_DAY_WEDNESDAY_1600_1800, MA1101R_THURSDAY_1600_1800, MA1521_FRIDAY_1600_1800));
    }
}
