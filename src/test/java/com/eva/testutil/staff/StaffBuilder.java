package com.eva.testutil.staff;

import java.util.HashSet;
import java.util.Set;

import com.eva.commons.util.DateUtil;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.model.tag.Tag;
import com.eva.model.util.SampleDataUtil;

public class StaffBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SINGLE_LEAVE_START_DATE = "01/01/2016";
    public static final String DEFAULT_LEAVE_END_DATE = "10/10/2016";
    public static final String DEFAULT_LEAVE_START_DATE = "12/10/2016";
    public static final int DEFAULT_LEAVE_TAKEN = 4;
    public static final String DEFAULT_TAG = "Tech";
    public static final String DEFAULT_COMMENT_TITLE = "Hardworking";
    public static final String DEFAULT_COMMENT_DATE = "03/04/2020";
    public static final String DEFAULT_COMMENT_DESCRIPTION = "Works hard";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private LeaveTaken leaveTaken;
    private Set<Tag> tags;
    private Set<Comment> comments;
    private Set<Leave> leaves;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        leaveTaken = new LeaveTaken(DEFAULT_LEAVE_TAKEN);
        tags = new HashSet<>();
        tags.add(new Tag(DEFAULT_TAG));
        comments = new HashSet<>();
        comments.add(new Comment(
                DateUtil.dateParsed(DEFAULT_COMMENT_DATE), DEFAULT_COMMENT_DESCRIPTION, DEFAULT_COMMENT_TITLE));
        leaves = new HashSet<>();
        leaves.add(new Leave(DEFAULT_SINGLE_LEAVE_START_DATE));
        leaves.add(new Leave(DEFAULT_LEAVE_START_DATE, DEFAULT_LEAVE_END_DATE));
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     *
     * @param staffToCopy
     */
    public StaffBuilder(Staff staffToCopy) {
        name = staffToCopy.getName();
        phone = staffToCopy.getPhone();
        email = staffToCopy.getEmail();
        address = staffToCopy.getAddress();
        leaveTaken = staffToCopy.getLeaveTaken();
        tags = new HashSet<>(staffToCopy.getTags());
        comments = new HashSet<>(staffToCopy.getComments());
        leaves = new HashSet<>(staffToCopy.getLeaves());
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     *
     * Parses the {@code comments} into a {@code Set<Comment>} and set it to the {@code Person} that we are building.
     */
    public StaffBuilder withComments(String... comments) {
        this.comments = SampleDataUtil.getCommentSet(comments);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code leaves} into a {@code Set<Leave>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withLeaves(String[]... leaves) {
        this.leaves = SampleDataUtil.getLeaveSet(leaves);
        return this;
    }

    /**
     * Parses the {@code leaveTaken} into a {@code LeaveTaken} and set it to the {@code Staff} that we are building.
     * This is not a method that should be used by anything other than storage.
     */
    public StaffBuilder withLeaveTaken(int leaveTaken) {
        this.leaveTaken = new LeaveTaken(leaveTaken);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, address, tags, leaves, comments);
    }
}

