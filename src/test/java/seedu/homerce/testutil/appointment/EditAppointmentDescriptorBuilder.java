package seedu.homerce.testutil.appointment;

import seedu.homerce.logic.commands.appointment.EditAppointmentCommand;
import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

public class EditAppointmentDescriptorBuilder {
    private EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code AppointmentTemp}'s details
     */
    public EditAppointmentDescriptorBuilder(AppointmentTemp appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setDate(appointment.getAppointmentDate());
        descriptor.setTimeOfDay(appointment.getAppointmentStartTime());
        descriptor.setPhone(appointment.getPhone());
        descriptor.setServiceCode(appointment.getServiceCode());
    }

    /**
     * Sets the {@code Description} of the {@Code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code IsFixed} of the {@Code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withTime(String time) {
        descriptor.setTimeOfDay(new TimeOfDay(time));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@Code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code ServiceCode} of the {@Code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withServiceCode(String serviceCode) {
        descriptor.setServiceCode(new ServiceCode(serviceCode));
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
