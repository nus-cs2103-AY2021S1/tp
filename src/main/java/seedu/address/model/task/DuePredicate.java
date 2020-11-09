package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public abstract class DuePredicate implements Predicate<Task> {
    protected final LocalDateTime deadline;
    private final String strDeadline;

    /**
     * Converts deadline into a LocalDateTime type.
     *
     * @param strDeadline string format of date + time
     */
    public DuePredicate(String strDeadline) {
        this.strDeadline = strDeadline;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(strDeadline, dateTimeFormat);
        this.deadline = deadline;
    }

    public String getDateTime() {
        return this.strDeadline;
    }

    public abstract boolean test(Task task);

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueBeforePredicate // instanceof handles nulls
                && deadline.equals(((DueBeforePredicate) other).deadline)); // state check
    }

}
