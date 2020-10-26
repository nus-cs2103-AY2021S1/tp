package seedu.resireg.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Students (Students are considered duplicates if they have the
 * same identity).
 */
public class DuplicateBinItemException extends RuntimeException {
    public DuplicateBinItemException() {
        super("Operation would result in duplicate students");
    }
}
