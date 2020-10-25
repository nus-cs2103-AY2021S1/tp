package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Policy (Policys are considered duplicates if they have the same
 * name).
 */
public class DuplicatePolicyException extends RuntimeException {
    public DuplicatePolicyException() {
        super("Operation will result in a duplicate Policy");
    }
}
