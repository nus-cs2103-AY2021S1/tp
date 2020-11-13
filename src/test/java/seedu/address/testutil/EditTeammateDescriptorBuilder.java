package seedu.address.testutil;

import seedu.address.logic.commands.project.EditTeammateCommand.EditTeammateDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditTeammateDescriptorBuilder {

    private EditTeammateDescriptor descriptor;

    public EditTeammateDescriptorBuilder() {
        descriptor = new EditTeammateDescriptor();
    }

    public EditTeammateDescriptorBuilder(EditTeammateDescriptor descriptor) {
        this.descriptor = new EditTeammateDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTeammateDescriptor} with fields containing {@code Person} (Teammate's) details
     */
    public EditTeammateDescriptorBuilder(Person teammate) {
        descriptor = new EditTeammateDescriptor();
        descriptor.setGitUserName(teammate.getGitUserName());
        descriptor.setTeammateName(teammate.getPersonName());
        descriptor.setPhone(teammate.getPhone());
        descriptor.setEmail(teammate.getEmail());
        descriptor.setAddress(teammate.getAddress());
    }

    /**
     * Sets the {@code teammateName} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withTeammatetName(String teammamteName) {
        descriptor.setTeammateName(new PersonName (teammamteName));
        return this;
    }

    /**
     * Sets the {@code phone} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code email} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code address} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditTeammateDescriptor build() {
        return descriptor;
    }


}
