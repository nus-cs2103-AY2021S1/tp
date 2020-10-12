package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Tutorial Groups
 * (Tutorial Groups are considered the same if they have the same Id.)
 *
 */
public class DuplicateTutorialGroupException extends RuntimeException {
    public DuplicateTutorialGroupException() {
        super("Operation would result in duplicate tutorial groups");
    }
}
