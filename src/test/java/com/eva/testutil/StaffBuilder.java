package com.eva.testutil;

import java.util.HashSet;
import java.util.Set;

import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.util.SampleDataUtil;

public class StaffBuilder extends PersonBuilder {

    public static final String DEFAULT_LEAVE_ONLY_STARTDATE = "12/12/2020";

    private Set<Leave> leaves;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        super();
        leaves = new HashSet<>();
        leaves.add(new Leave(DEFAULT_LEAVE_ONLY_STARTDATE));
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     *
     * @param staffToCopy
     */
    public StaffBuilder(Staff staffToCopy) {
        super(staffToCopy);
        leaves = new HashSet<>(staffToCopy.getLeaves());
    }

    /**
     * Parses the {@code leaves} into a {@code Set<Leave>} and set it to the {@code Staff} that we are building.
     */
    public PersonBuilder withSingleDateLeaves(String... leaves) {
        this.leaves = SampleDataUtil.getLeaveSet(leaves);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, address, tags, leaves, comments);
    }

}
