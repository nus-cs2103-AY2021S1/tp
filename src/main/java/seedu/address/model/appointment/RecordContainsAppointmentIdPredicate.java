package seedu.address.model.appointment;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code id} matches the given id.
 */
public class RecordContainsAppointmentIdPredicate implements Predicate<Appointment> {
    private final AppointmentId id;

    public RecordContainsAppointmentIdPredicate(AppointmentId id) {
        this.id = id;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getId().equals(id);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsAppointmentIdPredicate // instanceof handles nulls
                && this.id.equals(((RecordContainsAppointmentIdPredicate) other).id)); // state check
    }
}
