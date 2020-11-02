package com.eva.model.person.staff;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.model.tag.Tag;


public class Staff extends Person {
    /**
     * Leave balance is set to DEFAULT
     */
    private final LeaveTaken leaveTaken;
    private final Set<Leave> leaves = new HashSet<>();


    /**
     * Every field must be present and not null.
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param comments
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Comment> comments) {
        super(name, phone, email, address, tags, comments);
        leaveTaken = new LeaveTaken();
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

    public Staff(Name name, Phone phone, Email email, Address address,
                 Set<Tag> tags, Set<Leave> leaves, Set<Comment> comments) {
        super(name, phone, email, address, tags, comments);
        requireNonNull(leaves);
        this.leaves.addAll(leaves);
        leaveTaken = new LeaveTaken();
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

    public Staff(Name name, Phone phone, Email email, Address address, LeaveTaken leaveTaken,
                 Set<Tag> tags, Set<Leave> leaves, Set<Comment> comments) {
        super(name, phone, email, address, tags, comments);
        requireNonNull(leaves);
        this.leaves.addAll(leaves);
        this.leaveTaken = leaveTaken;
    }

    /**
     * Creates a Staff object from a Person Object.
     * @param person
     * @param leaves
     */
    public Staff(Person person, Set<Leave> leaves) {
        this(person.getName(), person.getPhone(), person.getEmail(),
                person.getAddress(), person.getTags(), leaves, person.getComments());
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public LeaveTaken getLeaveTaken() {
        return leaveTaken;
    }

}
