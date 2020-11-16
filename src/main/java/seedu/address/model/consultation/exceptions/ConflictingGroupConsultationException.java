package seedu.address.model.consultation.exceptions;

/**
 * Signals that the operation will result in conflicting personal Consultations
 * (Consultations are said to conflict if they match in time & date, but have various types such as
 * personal vs personal, group vs personal).
 */
public class ConflictingGroupConsultationException extends RuntimeException {
    public ConflictingGroupConsultationException() {
        super("Group consultations sharing the same time slot should be at the same location!");
    }
}
