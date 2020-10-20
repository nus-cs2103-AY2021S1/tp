package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.WorkerEditCommand.EditWorkerDescriptor;
import seedu.address.model.tag.Role;
//import seedu.address.model.tag.Tag;
import seedu.address.model.worker.Address;
//import seedu.address.model.worker.Email;
import seedu.address.model.worker.Name;
import seedu.address.model.worker.Pay;
import seedu.address.model.worker.Phone;
import seedu.address.model.worker.Worker;

/**
 * A utility class to help with building EditWorkerDescriptor objects.
 */
public class EditWorkerDescriptorBuilder {

    private EditWorkerDescriptor descriptor;

    public EditWorkerDescriptorBuilder() {
        descriptor = new EditWorkerDescriptor();
    }

    public EditWorkerDescriptorBuilder(EditWorkerDescriptor descriptor) {
        this.descriptor = new EditWorkerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditWorkerDescriptor} with fields containing {@code worker}'s details
     */
    public EditWorkerDescriptorBuilder(Worker worker) {
        descriptor = new EditWorkerDescriptor();
        descriptor.setName(worker.getName());
        descriptor.setPhone(worker.getPhone());
        descriptor.setPay(worker.getPay());
        //descriptor.setEmail(worker.getEmail());
        descriptor.setAddress(worker.getAddress());
        descriptor.setRoles(worker.getRoles());
    }

    /**
     * Sets the {@code Name} of the {@code EditWorkerDescriptor} that we are building.
     */
    public EditWorkerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditWorkerDescriptor} that we are building.
     */
    public EditWorkerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Pay} of the {@code EditWorkerDescriptor} that we are building.
     */
    public EditWorkerDescriptorBuilder withPay(String pay) {
        descriptor.setPay(new Pay(pay));
        return this;
    }

    /*
    public EditWorkerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }
     */

    /**
     * Sets the {@code Address} of the {@code EditWorkerDescriptor} that we are building.
     */
    public EditWorkerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code EditWorkerDescriptor}
     * that we are building.
     */
    public EditWorkerDescriptorBuilder withRoles(String... roles) {
        Set<Role> roleSet = Stream.of(roles).map(Role::createRole).collect(Collectors.toSet());
        descriptor.setRoles(roleSet);
        return this;
    }

    public EditWorkerDescriptor build() {
        return descriptor;
    }

}
