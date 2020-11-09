package seedu.address.model.task;

/**
 * Checks for every task (To-do and event) that is due before the given date and time.
 */
public class DueBeforePredicate extends DuePredicate {
    public DueBeforePredicate(String strDeadline) {
        super(strDeadline);
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isBefore(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueBeforePredicate // instanceof handles nulls
                && deadline.equals(((DueBeforePredicate) other).deadline)); // state check
    }
}
