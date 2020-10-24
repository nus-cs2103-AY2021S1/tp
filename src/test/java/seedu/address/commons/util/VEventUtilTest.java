package seedu.address.commons.util;

import jfxtras.icalendarfx.components.VEvent;
import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.VEventBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.FIRST_APP;
import static seedu.address.testutil.TypicalAppointments.SECOND_APP;
import static seedu.address.testutil.TypicalVEvents.FIRST_VEVENT;
import static seedu.address.testutil.TypicalVEvents.SECOND_VEVENT;


public class VEventUtilTest {

    //---------------- Tests for appsToVEventMapper --------------------------------------

    @Test
    public void appsToVEventMapper_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VEventUtil.appsToVEventsMapper(null));
    }

    @Test
    public void appsToVEventMapper_emptyAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VEventUtil.appsToVEventsMapper(new ArrayList<>()));
    }

    //---------------- Tests for appToVEventMapper --------------------------------------

    @Test
    public void appToVEventMapper_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VEventUtil.appToVEventMapper(null));
    }

    //---------------- Tests for isSameVEvent --------------------------------------

    @Test
    public void isSameVEvent() {

        // same object -> returns true
        assertTrue(VEventUtil.isSameVEvent(FIRST_VEVENT, FIRST_VEVENT));

        // null -> returns false
        assertTrue(VEventUtil.isSameVEvent(FIRST_VEVENT, null));

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
