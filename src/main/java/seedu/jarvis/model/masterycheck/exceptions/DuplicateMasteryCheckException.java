package seedu.jarvis.model.masterycheck.exceptions;

/**
 * Signals that the operation will result in duplicate MasteryChecks
 * (MasteryChecks are considered duplicates if they have the same identity fields, studentName and dateAndTime).
 */
public class DuplicateMasteryCheckException extends RuntimeException {
    public DuplicateMasteryCheckException() {
        super("Operation would result in duplicate mastery check");
    }
}
