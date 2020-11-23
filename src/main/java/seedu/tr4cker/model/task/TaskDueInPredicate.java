package seedu.tr4cker.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Predicate;

import seedu.tr4cker.model.util.GotoDateUtil;

/**
 * Tests that a {@code Task}'s {@code Deadline} is the deadline user wanted.
 */
public class TaskDueInPredicate implements Predicate<Task> {

    private final LocalDate dueDate;

    /** Constructor for TaskDueInPredicate with dueDate to be current time. */
    public TaskDueInPredicate() {
        this.dueDate = GotoDateUtil.getToday();
    }

    /** Constructor for TaskDueInPredicate with dueDate to be inputted date. */
    public TaskDueInPredicate(LocalDate dueDate) {
        requireNonNull(dueDate);
        this.dueDate = dueDate;
    }

    /** Constructor for TaskDueInPredicate with dueDate to be inputted month. */
    public TaskDueInPredicate(YearMonth month) {
        requireNonNull(month);
        this.dueDate = month.atDay(1);
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        LocalDate taskDate = task.getDeadline().getDateTime().toLocalDate();
        return taskDate.isEqual(dueDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDueInPredicate) // instanceof handles null
                && this.dueDate.equals(((TaskDueInPredicate) other).dueDate);
    }

}
