package seedu.resireg.model.alias.exceptions;

/**
 * Signals that the operation will result in duplicate command word aliases (Command word aliases are considered
 * duplicates if they have the same alias).
 */
public class DuplicateCommandWordAliasException extends RuntimeException {
    public DuplicateCommandWordAliasException() {
        super("Operation would result in duplicate aliases");
    }
}
