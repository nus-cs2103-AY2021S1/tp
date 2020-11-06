package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSlots.EMPTY_MONDAY_1600_1700;
import static seedu.address.testutil.TypicalSlots.EMPTY_MONDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.EMPTY_MONDAY_1630_1730;
import static seedu.address.testutil.TypicalSlots.EMPTY_MONDAY_1700_1800;
import static seedu.address.testutil.TypicalSlots.EMPTY_TUESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.EMPTY_TUESDAY_1630_1730;
import static seedu.address.testutil.TypicalSlots.EMPTY_TUESDAY_1800_2000;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.MA1101R_THURSDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.MA1101R_WEDNESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.MA1521_FRIDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.MA1521_FRIDAY_1800_2000;
import static seedu.address.testutil.TypicalSlots.UPPER_BODY_WEDNESDAY_1600_1800;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SlotBuilder;

public class SlotTest {

    @Test
    public void hasOverlapDuration() {

        // same day, same duration -> returns true
        assertTrue(EMPTY_MONDAY_1600_1700.hasOverlapDuration(EMPTY_MONDAY_1600_1700));

        // same day, overlap duration -> returns true
        assertTrue(EMPTY_MONDAY_1630_1730.hasOverlapDuration(EMPTY_MONDAY_1600_1700));
        assertTrue(EMPTY_MONDAY_1630_1730.hasOverlapDuration(EMPTY_MONDAY_1700_1800));
        assertTrue(EMPTY_MONDAY_1630_1730.hasOverlapDuration(EMPTY_MONDAY_1600_1800));
        assertTrue(EMPTY_MONDAY_1600_1800.hasOverlapDuration(EMPTY_MONDAY_1600_1700));
        assertTrue(EMPTY_MONDAY_1600_1800.hasOverlapDuration(EMPTY_MONDAY_1700_1800));

        // same day, different duration -> returns false
        assertFalse(EMPTY_MONDAY_1600_1700.hasOverlapDuration(EMPTY_MONDAY_1700_1800));

        // different day, same duration -> returns false
        assertFalse(EMPTY_MONDAY_1600_1800.hasOverlapDuration(EMPTY_TUESDAY_1600_1800));

        // different day, different duration -> returns false
        assertFalse(EMPTY_MONDAY_1600_1800.hasOverlapDuration(EMPTY_TUESDAY_1630_1730));
        assertFalse(EMPTY_MONDAY_1600_1800.hasOverlapDuration(EMPTY_TUESDAY_1800_2000));
    }

    @Test
    public void isSameSlot() {

        // same day, same duration -> returns true
        assertTrue(LEG_DAY_WEDNESDAY_1600_1800.isSameSlot(LEG_DAY_WEDNESDAY_1600_1800)); // same object
        assertTrue(LEG_DAY_WEDNESDAY_1600_1800.isSameSlot(UPPER_BODY_WEDNESDAY_1600_1800));

        // same day, different duration -> returns false
        assertFalse(MA1521_FRIDAY_1600_1800.isSameSlot(MA1521_FRIDAY_1800_2000));

        // different day, same duration -> returns false
        assertFalse(MA1101R_WEDNESDAY_1600_1800.isSameSlot(MA1101R_THURSDAY_1600_1800));

        // different day, different duration -> returns false
        assertFalse(MA1101R_THURSDAY_1600_1800.isSameSlot(MA1521_FRIDAY_1800_2000));
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(LEG_DAY_WEDNESDAY_1600_1800.equals(LEG_DAY_WEDNESDAY_1600_1800));

        // different object, same fields -> returns true
        assertTrue(LEG_DAY_WEDNESDAY_1600_1800.equals(new SlotBuilder(LEG_DAY_WEDNESDAY_1600_1800).build()));

        // contains null -> returns false
        assertFalse(LEG_DAY_WEDNESDAY_1600_1800.equals(null));

        // different activity -> returns false
        assertFalse(LEG_DAY_WEDNESDAY_1600_1800.equals(UPPER_BODY_WEDNESDAY_1600_1800));

        // different day -> returns false
        assertFalse(MA1101R_WEDNESDAY_1600_1800.equals(MA1101R_THURSDAY_1600_1800));

        // different duration -> returns false
        assertFalse(MA1521_FRIDAY_1600_1800.equals(MA1521_FRIDAY_1800_2000));

    }
}
