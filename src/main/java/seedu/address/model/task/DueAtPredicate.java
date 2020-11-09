package seedu.address.model.task;

/**
 * Checks for every task (To-do and event) that is due at the given date and time.
 */
public class DueAtPredicate extends DuePredicate {
    public DueAtPredicate(String strDeadline) {
        super(strDeadline);
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isEqual(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueAtPredicate // instanceof handles nulls
                && deadline.equals(((DueAtPredicate) other).deadline)); // state check
    }
}
