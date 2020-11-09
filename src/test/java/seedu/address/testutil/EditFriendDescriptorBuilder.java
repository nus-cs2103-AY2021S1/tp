package seedu.address.testutil;

import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.model.commons.Name;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;


public class EditFriendDescriptorBuilder {
    private EditDescriptor descriptor;

    public EditFriendDescriptorBuilder() {
        descriptor = new EditDescriptor();
    }

    public EditFriendDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFriendDescriptor} with fields containing {@code person}'s details
     */
    public EditFriendDescriptorBuilder(Friend friend) {
        descriptor = new EditDescriptor();
        descriptor.setName(friend.getName());
        descriptor.setMobile(friend.getMobile());
        descriptor.setPassport(friend.getPassport());
    }

    /**
     * Sets the {@code Name} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withMobile(String mobile) {
        descriptor.setMobile(new Mobile(mobile));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withPassport(String passport) {
        descriptor.setPassport(new Passport(passport));
        return this;
    }

    public EditDescriptor build() {
        return descriptor;
    }
}
