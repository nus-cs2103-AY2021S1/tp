package seedu.address.model.project.exceptions;

/**
 * Signals that the operation will result in duplicate participations (participations are considered duplicates
 * if they have the same identity).
 */
public class DuplicateParticipationException extends RuntimeException {
    public DuplicateParticipationException() {
        super("Operation would result in duplicate participations");
    }
}
