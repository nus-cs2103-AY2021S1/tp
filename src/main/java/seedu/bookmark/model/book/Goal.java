package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Goal {
    public static final String MESSAGE_CONSTRAINTS =
            "Page should only contain numeric characters,\n "
                    + "Deadline format is DD-MM-YYYY\n"
                    + "both page and deadline should not be empty";
    public static final String UI_COMPLETED = " (Completed!)";
    public static final String UI_OVERDUE = " (Overdue!)";
    public static final String UI_IN_PROGRESS = " (In Progress!)";
    /*
        Goal must be in the format: [page] [deadline]
        Deadline must be specified as dd-mm-yyyy
        Both fields must not be empty.
     */
    public static final String DEADLINE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    public static final String DEFAULT_GOAL_STRING = "0 31-12-9999";
    public static final String PAGE_REGEX = "^\\d+";

    public final String page;
    public final String deadline;

    /**
     * Construct a {@code Goal}.
     */
    public Goal(String page, String deadline) {
        requireNonNull(page, deadline);
        this.page = page;
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor to construct {@code Goal} object
     * @param goal
     */
    public Goal(String goal) {
        requireNonNull(goal);
        String[] parts = goal.split("\\s+");
        this.page = parts[0];
        this.deadline = parts[1];
    }

    public static Goal defaultGoal() { // constructor for default goal
        return new Goal("0", "31-12-9999");
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPage() {
        return page;
    }

    public int getPageInt() {
        return Integer.parseInt(page);
    }

    /**
     * Checks if a {@code Goal} object is overdue compared to today's date.
     * @return true if a goal is already overdue.
     */
    public boolean isOverdue() {
        LocalDate now = LocalDate.now();
        return parseDeadline(deadline).isBefore(now);
    }

    /**
     * Returns if a given string is a valid genre.
     */
    public static boolean isValidGoal(String page, String deadline) {
        return page.matches(PAGE_REGEX) && isValidDeadline(deadline);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Goal) {
            String otherPage = ((Goal) other).page;
            String otherDeadline = ((Goal) other).deadline;

            return deadline.equals(otherDeadline) && page.equals(otherPage);
        }
        return false;
    }

    /**
     * Returns true if deadline has the correct format and valid date and time
     * @param deadline string deadline provided
     * @return boolean
     */
    private static boolean isValidDeadline(String deadline) {
        if (!deadline.matches(DEADLINE_REGEX)) {
            return false;
        } else {
            try {
                parseDeadline(deadline);
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return true;
    }

    private static LocalDate parseDeadline(String deadline) throws DateTimeParseException {
        requireNonNull(deadline);
        String[] parts = deadline.split("-");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        String deadlineLocalDateFormat = String.format("%s-%s-%s", year, month, day);

        return LocalDate.parse(deadlineLocalDateFormat);
    }

    private static String getDateDescription(String deadline) {
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("en"));
        LocalDate date = parseDeadline(deadline);
        return date.format(oldFormat);
    }

    @Override
    public String toString() {
        if (this.equals(Goal.defaultGoal())) {
            return "Currently no goal";
        }
        return String.format("Finish page %s before %s", page, getDateDescription(deadline));
    }

    @Override
    public int hashCode() {
        return String.format("%s %s", page, deadline).hashCode();
    }


}
