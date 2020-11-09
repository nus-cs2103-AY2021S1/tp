package tp.acecs2103.model.task;


import java.time.LocalDate;


/**
 * Represents a Task's official deadline in the task manager.
 * Guarantees: is valid as declared in {@link #isValidDeadline(String)}
 */

public class OfficialDeadline extends Deadline {


    /**
     * Constructs an {@code OfficialDeadline}.
     *
     * @param officialDeadline A valid official deadline in String form.
     * @param timeInfo A valid official deadline in LocalTime form.
     */
    public OfficialDeadline(String officialDeadline, LocalDate timeInfo) {
        super(officialDeadline, timeInfo);
    }


    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof OfficialDeadline)) {
            return false;
        } else {
            return this.value.equals(((OfficialDeadline) other).value)
                    && this.timeInfo.equals(((OfficialDeadline) other).timeInfo);
        }
    }

}
