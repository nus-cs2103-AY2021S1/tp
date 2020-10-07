package com.eva.model.person.staff.leave;

import static com.eva.commons.util.IntegerUtil.requirePositive;

/**
 * Encapsulates a staff's leave balance.
 */
public class LeaveBalance {
    private static final DefaultLeaveBalance DEFAULT_LEAVE_BALANCE = new DefaultLeaveBalance();

    private final int leaveBalance;

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

    public static void setDefaultLeaveBalance(int newDefaultLeaveBalance) {
        requirePositive(newDefaultLeaveBalance);
        DEFAULT_LEAVE_BALANCE.setDefaultLeave(newDefaultLeaveBalance);
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }
}
