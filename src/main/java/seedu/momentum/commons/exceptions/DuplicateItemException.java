//@@author boundtotheearth

package seedu.momentum.commons.exceptions;

/**
 * Signals that the operation will result in duplicate items (Items are considered
 * duplicates if they have the same identity).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate items");
    }
}
