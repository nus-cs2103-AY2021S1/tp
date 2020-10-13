package com.eva.model.person.staff;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveBalance;
import com.eva.model.tag.Tag;


public class Staff extends Person {
    /**
     * Leave balance is set to DEFAULT
     */
    private final LeaveBalance leaveBalance = new LeaveBalance();
    private final Set<Leave> leaves = new HashSet<>();


    /**
     * Every field must be present and not null.
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    /**
     * Every field must be present and not null.
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param leaves
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Leave> leaves) {
        super(name, phone, email, address, tags);
        requireNonNull(leaves);
        this.leaves.addAll(leaves);
    }


    public Staff(Person person, Set<Leave> leaves) {
        this(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags(), leaves);
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public LeaveBalance getLeaveBalance() {
        return leaveBalance;
    }
}
