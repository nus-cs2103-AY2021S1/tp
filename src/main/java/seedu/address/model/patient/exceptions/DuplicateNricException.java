package seedu.address.model.patient.exceptions;

/**
 * Signals that the operation will result in duplicate NRICs
 */
public class DuplicateNricException extends RuntimeException {
    public DuplicateNricException() {
        super("Operation would result in duplicate NRICs");
    }
}
