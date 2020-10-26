package com.eva.model.current.view;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.UniqueLeaveList;
import com.eva.model.person.staff.leave.exceptions.LeaveNotFoundException;

import javafx.collections.ObservableList;

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

    public ObservableList<Leave> getLeaveList() throws LeaveNotFoundException {
        if (currentView == null) {
            throw new LeaveNotFoundException();
        }
        return leaves.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return leaves.asUnmodifiableObservableList().size() + " leaves";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentViewStaff // instanceof handles nulls
                && leaves.equals(((CurrentViewStaff) other).leaves)
                && currentView.equals(((CurrentViewStaff) other).currentView));
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentView, leaves);
    }
}
