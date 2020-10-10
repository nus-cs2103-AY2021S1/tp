package chopchop.model.exceptions;

/**
 * Signals that the operation will result in duplicate Ingredients (Persons are considered duplicates if they have
 * the same identity).
 */
public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException() {
        super("Operation would result in duplicate food entry");
    }
}
