package com.eva.testutil.staff;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.eva.logic.commands.EditCommand;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;
import com.eva.model.util.SampleDataUtil;

public class EditStaffDescriptorBuilder {
    private EditCommand.EditPersonDescriptor descriptor;

    public EditStaffDescriptorBuilder() {
        descriptor = new EditCommand.EditPersonDescriptor();
    }

    public EditStaffDescriptorBuilder(EditCommand.EditPersonDescriptor descriptor) {
        this.descriptor = new EditCommand.EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditStaffDescriptorBuilder(Staff staff) {
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setName(staff.getName());
        descriptor.setPhone(staff.getPhone());
        descriptor.setEmail(staff.getEmail());
        descriptor.setAddress(staff.getAddress());
        descriptor.setLeaveTaken(staff.getLeaveTaken());
        descriptor.setTags(staff.getTags());
        descriptor.setLeaves(staff.getLeaves());
        descriptor.setComments(staff.getComments());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditStaffDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditStaffDescriptorBuilder withLeave(String[]... leaves) {
        Set<Leave> leaveSet = SampleDataUtil.getLeaveSet(leaves);
        descriptor.setLeaves(leaveSet);
        return this;
    }

    public EditCommand.EditPersonDescriptor build() {
        return descriptor;
    }
}
