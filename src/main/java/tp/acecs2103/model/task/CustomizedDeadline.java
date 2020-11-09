package tp.acecs2103.model.task;

import java.time.LocalDate;

/**
 * Represents a Task's official deadline in the task manager.
 * Guarantees: is valid as declared in {@link #isValidDeadline(String)}
 */

public class CustomizedDeadline extends Deadline {
    /**
     * Constructs an {@code OfficialDeadline}.
     *
     * @param customizedDeadline A valid customized deadline in String form.
     * @param timeInfo A valid customized deadline in LocalTime form.
     */
    public CustomizedDeadline(String customizedDeadline, LocalDate timeInfo) {
        super(customizedDeadline, timeInfo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof CustomizedDeadline)) {
            return false;
        } else {
            return this.value.equals(((CustomizedDeadline) other).value)
                    && this.timeInfo.equals(((CustomizedDeadline) other).timeInfo);
        }
    }
}
