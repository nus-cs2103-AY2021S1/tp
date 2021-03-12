package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ChangeCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;

public class EditAppointmentDescriptorTest {

    @Test
    public void equals() {
        Time sampleTime = new Time(LocalTime.of(13, 0));
        Duration sampleDuration = Duration.ofHours(1);
        Date sampleDate = new Date(LocalDate.of(2020, 1, 1));

        EditAppointmentDescriptor originalDescriptor =
                new EditAppointmentDescriptor(sampleDate, sampleTime, sampleDuration);

        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues =
                new EditAppointmentDescriptor(sampleDate, sampleTime, sampleDuration);
        assertTrue(originalDescriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(originalDescriptor.equals(originalDescriptor));

        // null -> returns false
        assertFalse(originalDescriptor.equals(null));

        // different types -> returns false
        assertFalse(originalDescriptor.equals(5));

        // different date -> returns false
        Date diffDate = new Date(LocalDate.of(2020, 2, 2));
        EditAppointmentDescriptor differentDescriptor =
                new EditAppointmentDescriptor(diffDate, sampleTime, sampleDuration);
        assertFalse(originalDescriptor.equals(differentDescriptor));

        // different time -> returns false
        Time diffTime = new Time(LocalTime.of(8, 0));
        differentDescriptor = new EditAppointmentDescriptor(sampleDate, diffTime, sampleDuration);
        assertFalse(originalDescriptor.equals(differentDescriptor));

        // different duration -> returns false
        Duration diffDuration = Duration.ofMinutes(30);
        differentDescriptor = new EditAppointmentDescriptor(sampleDate, sampleTime, diffDuration);
        assertFalse(originalDescriptor.equals(differentDescriptor));

        // all different values -> returns false
        differentDescriptor = new EditAppointmentDescriptor(diffDate, diffTime, diffDuration);
        assertFalse(originalDescriptor.equals(differentDescriptor));
    }
}
