package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditLogDescriptor;
import seedu.address.model.log.Address;
import seedu.address.model.log.Email;
import seedu.address.model.log.Log;
import seedu.address.model.log.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;

/**
 * A utility class to help with building EditLogDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCommand.EditLogDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditLogDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditLogDescriptor descriptor) {
        this.descriptor = new EditLogDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLogDescriptor} with fields containing {@code log}'s details
     */
    public EditPersonDescriptorBuilder(Log log) {
        descriptor = new EditCommand.EditLogDescriptor();
        descriptor.setName(log.getName());
        descriptor.setPhone(log.getPhone());
        descriptor.setEmail(log.getEmail());
        descriptor.setAddress(log.getAddress());
        descriptor.setTags(log.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditLogDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditLogDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditLogDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditLogDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditLogDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditLogDescriptor build() {
        return descriptor;
    }
}
