package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APP1;
import static seedu.address.testutil.TypicalVEvents.FIRST_VEVENT;
import static seedu.address.testutil.TypicalVEvents.SECOND_VEVENT;
import static seedu.address.testutil.TypicalVEvents.VEVENT1;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.testutil.VEventBuilder;

public class VEventUtilTest {

    //---------------- Tests for appsToVEventMapper --------------------------------------

    @Test
    public void appsToVEventMapper_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VEventUtil.appsToVEventsMapper(null));
    }

    @Test
    public void appsToVEventMapper_list_success() {
        List<VEvent> converted = VEventUtil.appsToVEventsMapper(Collections.singletonList(APP1));
        List<VEvent> expected = Collections.singletonList(VEVENT1);

        assertEquals(converted.size(), 1);
        assertTrue(VEventUtil.isSameVEvent(converted.get(0), expected.get(0)));
    }

    //---------------- Tests for appToVEventMapper --------------------------------------

    @Test
    public void appToVEventMapper_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VEventUtil.appToVEventMapper(null));
    }

    @Test
    public void appToVEventMapper_appointment_success() {
        VEvent converted = VEventUtil.appToVEventMapper(APP1);
        assertTrue(VEventUtil.isSameVEvent(VEVENT1, converted));
    }

    //---------------- Tests for isSameVEvent --------------------------------------

    @Test
    public void isSameVEvent() {

        // same object -> returns true
        assertTrue(VEventUtil.isSameVEvent(FIRST_VEVENT, FIRST_VEVENT));

        // null -> returns false
        assertFalse(VEventUtil.isSameVEvent(FIRST_VEVENT, null));

        // different patient name -> false
        VEvent editedFirst = new VEventBuilder(FIRST_VEVENT).withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertFalse(VEventUtil.isSameVEvent(FIRST_VEVENT, editedFirst));

        // different start time -> false
        editedFirst = new VEventBuilder(FIRST_VEVENT).withStartTime(VALID_START_TIME_SECOND).build();
        assertFalse(VEventUtil.isSameVEvent(FIRST_VEVENT, editedFirst));

        // different end time -> false
        editedFirst = new VEventBuilder(FIRST_VEVENT).withEndTime(VALID_END_TIME_SECOND).build();
        assertFalse(VEventUtil.isSameVEvent(FIRST_VEVENT, editedFirst));

        // different object, same patient name, start and end time -> true
        editedFirst = new VEventBuilder(SECOND_VEVENT).withPatientName(VALID_PATIENT_NAME_FIRST)
                .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
        assertTrue(VEventUtil.isSameVEvent(FIRST_VEVENT, editedFirst));

    }

}
