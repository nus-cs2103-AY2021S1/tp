package seedu.address.testutil;

import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;


/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setPatientName(appointment.getPatientName());
        descriptor.setPatientIc(appointment.getPatientIc());
        descriptor.setStartTime(appointment.getStartTime());
        descriptor.setDuration(appointment.getDuration());
    }

    /**
     * Sets the {@code Name} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientName(String patientName) {
        descriptor.setPatientName(new Name(patientName));
        return this;
    }

    /**
     * Sets the {@code IcNumber} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientIc(String patientIc) {
        descriptor.setPatientIc(new IcNumber(patientIc));
        return this;
    }

    /**
     * Sets the {@code AppointmentDateTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new AppointmentDateTime(startTime));
        return this;
    }

    /**
     * Sets the {@code int} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(Integer.parseInt(duration));
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
