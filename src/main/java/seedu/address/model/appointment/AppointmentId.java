package seedu.address.model.appointment;

import seedu.address.model.Id;

/**
 * Represents an appointment's ID.
 * Guarantees: immutable
 */
public class AppointmentId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment ID must a positive integer.";

    /**
     * Constructs an {@code AppointmentId}.
     *
     * @param id a valid appointment ID.
     */
    public AppointmentId(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentId // instanceof handles nulls
                && this.id == ((AppointmentId) other).id); // state check
    }
}
