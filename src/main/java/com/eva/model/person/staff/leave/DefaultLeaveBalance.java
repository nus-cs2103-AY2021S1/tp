package com.eva.model.person.staff.leave;

public class DefaultLeaveBalance {
    private static final int DEFAULT = 14;
    private int defaultLeave;

    /**
     *  Creates a default leave balance object.
     */
    DefaultLeaveBalance() {
        this.defaultLeave = DEFAULT;
    }

    public int getDefaultLeave() {
        return defaultLeave;
    }
    public void setDefaultLeave(int newDefaultLeave) {
        this.defaultLeave = newDefaultLeave;
    }
}
