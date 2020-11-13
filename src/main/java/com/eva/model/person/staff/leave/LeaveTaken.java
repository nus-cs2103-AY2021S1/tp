package com.eva.model.person.staff.leave;

import static com.eva.commons.util.IntegerUtil.requirePositive;

/**
 * Encapsulates a staff's number of leave's taken.
 */
public class LeaveTaken {
    private static final String MESSAGE_INVALID_LEAVE_DEDUCTION = "Deleting this leave will cause the number of"
            + " leaves taken to become negative.";

    private int days;

    /**
     * Creates a leave taken object with 0 leaves taken.
     */
    public LeaveTaken() {
        this.days = 0;
    }

    /**
     * Creates a leave balance object with the specified leave taken.
     * @param leaveTaken valid integer.
     */
    public LeaveTaken(int leaveTaken) {
        requirePositive(leaveTaken);
        this.days = leaveTaken;
    }

    public int getDays() {
        return days;
    }

    /**
     * Returns true if the number of leave days to deduct from a staff's leave taken counter will not cause it to
     * become negative.
     */
    public boolean isValidDeduction(int leaveDays) {
        return (days - leaveDays) > 0;
    }
    /**
     * Adds the given number of leave days to a staff's leave balance.
     * @param leaveDays must be a positive number;
     */
    public void addLeaveTaken(int leaveDays) {
        requirePositive(leaveDays);
        days += leaveDays;
    }

    /**
     * Deducts the given number of leave days from a staff's leave balance.
     * @param leaveDays must be a positive number.
     */
    public void deductLeaveTaken(int leaveDays) {
        requirePositive(leaveDays);
        if (isValidDeduction(leaveDays)) {
            days -= leaveDays;
        } else {
            throw new IllegalArgumentException(MESSAGE_INVALID_LEAVE_DEDUCTION);
        }
    }

    @Override
    public String toString() {
        return String.format("Number of leaves taken: %d days", days);
    }
}
