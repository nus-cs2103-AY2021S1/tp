package seedu.homerce.model.appointment.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.client.Phone;

public class AppointmentPhonePredicate implements Predicate<Appointment> {
    private final Phone phoneOfAppointment;

    public AppointmentPhonePredicate(Phone phone) {
        this.phoneOfAppointment = phone;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getClient().getPhone().value.contains(phoneOfAppointment.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentPhonePredicate // instanceof handles nulls
            && phoneOfAppointment.equals(((AppointmentPhonePredicate) other).phoneOfAppointment)); // state check
    }
}
