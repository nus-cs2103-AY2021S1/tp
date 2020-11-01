package seedu.resireg.model.bin.exceptions;

/**
 * Signals that the operation will result in duplicate Students (Students are considered duplicates if they have the
 * same identity).
 */
public class InvalidBinnedItemException extends RuntimeException {
    public InvalidBinnedItemException() {
        super("Operation would result in an invalid bin item being restored");
    }
}

