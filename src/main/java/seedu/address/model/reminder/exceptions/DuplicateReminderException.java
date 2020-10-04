package seedu.address.model.reminder.exceptions;

/**
 * Signals that the operation will result in duplicate Reminders.
 */
public class DuplicateReminderException extends RuntimeException {
    public DuplicateReminderException() {
        super("Operation would result in duplicate reminders.");
    }
}
