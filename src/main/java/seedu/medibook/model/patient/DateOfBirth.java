package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

import seedu.medibook.model.commonfields.Date;

/**
 * Represents a Patient's date of birth (DOB) in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth (DOB) should be a valid calendar date and of the format \"DD-MM-YYYY\" "
                    + "where D, M and Y represent digits of the day, month and year of the DOB respectively."
                    + "\nDOB should not be in the future.";
    private final String inputValue;
    private final String outputValue;
    private final Date date;


    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dobString A valid date of birth.
     */
    public DateOfBirth(String dobString) {
        requireNonNull(dobString);
        checkArgument(isValidDateOfBirth(dobString), MESSAGE_CONSTRAINTS);
        date = new Date(dobString, true);
        inputValue = date.inputValue;
        outputValue = date.outputValue;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String dobString) {
        if (!Date.isValidDate(dobString)) {
            return false;
        }
        try {
            new Date(dobString, true);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getInputString() {
        return inputValue;
    }

    public String getOutputString() {
        return outputValue;
    }

    @Override
    public String toString() {
        return outputValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && date.equals(((DateOfBirth) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
