package seedu.address.logic.commands.appointment;

import static seedu.address.testutil.TypicalAppointmentDates.PAST_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ModelStub;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentStatus;

class EditAppointmentCommandTest {
    private static final Appointment appt = new Appointment(
        ALICE,
        BENEDICT,
        PAST_APPOINTMENT_DATE,
        VALID_APPOINTMENT_TIME,
        AppointmentStatus.ACTIVE
    );

    class ModelStubSuccess extends ModelStub {
        ModelStubSuccess() {
        }

        @Override
        public Appointment getAppointmentById(AppointmentId appointmentId) {
            return appt;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            return false;
        }

        @Override
        public void setAppointment(Appointment target, Appointment changedAppointment) {
        }
    }

    @Test
    void execute() {
        EditAppointmentCommand.ChangedAppointmentDescriptor changedAppointmentDescriptor =
            new EditAppointmentCommand.ChangedAppointmentDescriptor();

        changedAppointmentDescriptor.setStatus(AppointmentStatus.COMPLETED);

        EditAppointmentCommand command = new EditAppointmentCommand(appt.getId(), changedAppointmentDescriptor);
        Assertions.assertDoesNotThrow(() -> command.execute(new ModelStubSuccess()));
    }

    @Test
    void equals() {
        EditAppointmentCommand.ChangedAppointmentDescriptor changedAppointmentDescriptor =
            new EditAppointmentCommand.ChangedAppointmentDescriptor();
        changedAppointmentDescriptor.setStatus(AppointmentStatus.COMPLETED);

        EditAppointmentCommand.ChangedAppointmentDescriptor otherChangedAppointmentDescriptor =
            new EditAppointmentCommand.ChangedAppointmentDescriptor();
        otherChangedAppointmentDescriptor.setStatus(AppointmentStatus.COMPLETED);

        Assertions.assertEquals(true, changedAppointmentDescriptor.equals(changedAppointmentDescriptor));
        Assertions.assertEquals(false, changedAppointmentDescriptor.equals(ALICE));
        Assertions.assertEquals(true, changedAppointmentDescriptor.equals(
            otherChangedAppointmentDescriptor
        ));
    }
}
