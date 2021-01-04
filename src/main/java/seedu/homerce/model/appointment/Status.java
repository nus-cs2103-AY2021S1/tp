package seedu.homerce.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.AppUtil.checkArgument;

/**
 * Status to indicate if the appointment is done i.e. Yes or No.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status of appointment should be either 'y' or 'n' to"
            + "indicate if the appointment is done or not, and it should not be blank.";

    private boolean value;

    /**
     * Constructs a {@code status}
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.value = status.equals("y");
    }

    public static boolean isValidStatus(String test) {
        return test.toLowerCase().equals("y") || test.toLowerCase().equals("n");
    }

    public void markDone() {
        this.value = true;
    }

    public void markUnDone() {
        this.value = false;
    }

    @Override
    public String toString() {
        return value ? "Yes" : "No";
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value == (((Status) other).value)); // state check
    }

    public boolean isDone() {
        return value;
    }
}
