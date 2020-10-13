package com.eva.model.person.staff.leave;

import static com.eva.commons.util.IntegerUtil.requirePositive;

/**
 * Encapsulates a staff's leave balance.
 */
public class LeaveBalance {
    public static final DefaultLeaveBalance DEFAULT_LEAVE_BALANCE = new DefaultLeaveBalance();

    private int leaveBalance;

    /**
     * Creates a leave balance object with the default leave balance.
     */
    public LeaveBalance() {
        this.leaveBalance = DEFAULT_LEAVE_BALANCE.getDefaultLeave();
    }

    /**
     * Creates a leave balance object with the specified leave balance.
     * @param leaveBalance valid integer.
     */
    public LeaveBalance(int leaveBalance) {
        requirePositive(leaveBalance);
        this.leaveBalance = leaveBalance;
    }

    /**
     * Replaces the default leave balance with the specified new {@code newDefaultLeaveBalance}.
     * TODO: improve implementation of how to change default leave (possibly a command).
     * @param newDefaultLeaveBalance must be a positive number.
     */
    public static void setDefaultLeaveBalance(int newDefaultLeaveBalance) {
        requirePositive(newDefaultLeaveBalance);
        DEFAULT_LEAVE_BALANCE.setDefaultLeave(newDefaultLeaveBalance);
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    /**
     * Adds the given number of leave days to a staff's leave balance.
     * @param leaveDays must be a positive number;
     */
    public void addLeaveBalance(int leaveDays) {
        requirePositive(leaveDays);
        leaveBalance += leaveDays;
    }

    /**
     * Deducts the given number of leave days from a staff's leave balance.
     * @param leaveDays must be a positive number.
     */
    public void deductLeaveBalance(int leaveDays) {
        requirePositive(leaveDays);
        leaveBalance -= leaveDays;
    }
}
