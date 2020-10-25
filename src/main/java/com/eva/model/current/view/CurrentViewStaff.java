package com.eva.model.current.view;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.UniqueLeaveList;

public class CurrentViewStaff {

    private final Staff currentView;
    private final UniqueLeaveList leaves;

    /**
     * Creates an empty currentViewStaff.
     */
    public CurrentViewStaff() {
        this.currentView = null;
        this.leaves = new UniqueLeaveList();
    }

    /**
     * Creates a currentView with the current viewing staff.
     */
    public CurrentViewStaff(Staff currentView) {
        requireNonNull(currentView);
        this.currentView = currentView;
        this.leaves = new UniqueLeaveList();
        this.leaves.fill(currentView.getLeaves());
    }

    public Optional<Staff> getCurrentView() {
        return Optional.ofNullable(currentView);
    }
}
