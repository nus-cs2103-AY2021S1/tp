package tp.acecs2103.model.task;

import static java.util.Objects.requireNonNull;
import static tp.acecs2103.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.logging.Logger;

import tp.acecs2103.AppParameters;
import tp.acecs2103.commons.core.LogsCenter;

public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be of the format of 'YYYY-MM-DD' and be consists of only numbers and '-'s. "
                    + "Range of dates is allowed from '2000-01-01' to '2099-12-31'";

    public static final String VALIDATION_REGEX = "^19|20\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    public final String value;
    public final LocalDate timeInfo;



    /**
     * Constructs an {@code OfficialDeadline}.
     */
    public Deadline(String deadline, LocalDate timeInfo) {
        if (deadline != null) {
            requireNonNull(deadline);
            checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        }
        this.value = deadline;
        this.timeInfo = timeInfo;
    }

    /**
     * Returns true if a given string is a valid official deadline.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public boolean contains(String keyword) {
        return value.contains(keyword);
    }

    public LocalDate getTimeInfo() {
        return timeInfo;
    }

    @Override
    public String toString() {
        return value;
    }

    public int compareTo(Deadline deadline) {
        return this.timeInfo.compareTo(deadline.getTimeInfo());
    }
}
