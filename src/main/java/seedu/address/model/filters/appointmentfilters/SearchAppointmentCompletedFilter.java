package seedu.address.model.filters.appointmentfilters;

import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s {@code Patient}'s {@code isCompleted} matches the Boolean provided.
 */
public class SearchAppointmentCompletedFilter implements Predicate<Appointment> {
    private final Boolean isCompleted;

    public SearchAppointmentCompletedFilter(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.isCompleted().equals(isCompleted);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchAppointmentCompletedFilter // instanceof handles nulls
                && isCompleted.equals(((SearchAppointmentCompletedFilter) other).isCompleted)); // state check
    }
}
