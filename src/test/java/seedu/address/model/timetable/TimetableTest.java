package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1630_1730;
import static seedu.address.testutil.TypicalSlots.MA1101R_THURSDAY_1600_1800;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class TimetableTest {

    private final Timetable timetable = new Timetable();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), timetable.getSlotList());
    }

    @Test
    public void hasSlot_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timetable.hasSlot(null));
    }

    @Test
    public void hasSlot_slotNotInTimetable_returnsFalse() {
        assertFalse(timetable.hasSlot(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void hasSlot_slotInTimetable_returnsTrue() {
        timetable.addSlot(LEG_DAY_WEDNESDAY_1600_1800);
        assertTrue(timetable.hasSlot(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void hasOverlapDuration_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timetable.hasOverlapDuration(null));
    }

    @Test
    public void hasOverlapDuration_slotDoesNotOverlap_returnsFalse() {
        timetable.addSlot(LEG_DAY_WEDNESDAY_1600_1800);
        assertFalse(timetable.hasOverlapDuration(MA1101R_THURSDAY_1600_1800));
    }

    @Test
    public void hasOverlapDuration_slotOverlap_returnsTrue() {
        timetable.addSlot(LEG_DAY_WEDNESDAY_1600_1800);
        assertTrue(timetable.hasOverlapDuration(LEG_DAY_WEDNESDAY_1630_1730));
    }

    @Test
    public void getSlotList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> timetable.getSlotList().remove(0));
    }
}
