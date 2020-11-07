package com.eva.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.eva.logic.commands.CommentCommand;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

public class CommentPersonDescriptorBuilder {
    private CommentCommand.CommentPersonDescriptor descriptor;

    public CommentPersonDescriptorBuilder() {
        descriptor = new CommentCommand.CommentPersonDescriptor();
    }

    public CommentPersonDescriptorBuilder(CommentCommand.CommentPersonDescriptor descriptor) {
        this.descriptor = new CommentCommand.CommentPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public CommentPersonDescriptorBuilder(Person person) {
        descriptor = new CommentCommand.CommentPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setComments(person.getComments());
        if (person instanceof Staff) {
            Set<Leave> updatedLeaves = ((Staff) person).getLeaves();
            descriptor.setLeaves(updatedLeaves);
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public com.eva.testutil.CommentPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public com.eva.testutil.CommentPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public com.eva.testutil.CommentPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public com.eva.testutil.CommentPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public com.eva.testutil.CommentPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public CommentCommand.CommentPersonDescriptor build() {
        return descriptor;
    }
}
