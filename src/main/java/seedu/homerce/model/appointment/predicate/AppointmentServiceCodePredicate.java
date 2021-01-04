package seedu.homerce.model.appointment.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.service.ServiceCode;

public class AppointmentServiceCodePredicate implements Predicate<Appointment> {
    private final ServiceCode serviceCodeOfAppointment;

    public AppointmentServiceCodePredicate(ServiceCode serviceCodeOfAppointment) {
        this.serviceCodeOfAppointment = serviceCodeOfAppointment;
    }

    @Override
    public boolean test(Appointment appointment) {
        return serviceCodeOfAppointment.equals(appointment.getService().getServiceCode());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentServiceCodePredicate // instanceof handles nulls
            && serviceCodeOfAppointment.equals(((AppointmentServiceCodePredicate) other)
            .serviceCodeOfAppointment)); // state check
    }
}
