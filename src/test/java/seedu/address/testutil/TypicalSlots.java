package seedu.address.testutil;

import static seedu.address.model.timetable.Day.MONDAY;
import static seedu.address.model.timetable.Day.TUESDAY;
import static seedu.address.model.timetable.Day.WEDNESDAY;
import static seedu.address.model.timetable.Day.THURSDAY;
import static seedu.address.model.timetable.Day.FRIDAY;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1700;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;
import static seedu.address.model.timetable.DurationTest.DURATION_1630_1730;
import static seedu.address.model.timetable.DurationTest.DURATION_1700_1800;
import static seedu.address.model.timetable.DurationTest.DURATION_1800_2000;
import static seedu.address.testutil.TypicalLessons.MA1101R;
import static seedu.address.testutil.TypicalLessons.MA1521;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;


import seedu.address.model.timetable.Activity;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.DurationTest;
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

    private TypicalSlots() {}
}
