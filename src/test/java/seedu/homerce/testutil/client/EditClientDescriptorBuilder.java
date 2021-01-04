package seedu.homerce.testutil.client;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.homerce.logic.commands.client.EditClientCommand;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Email;
import seedu.homerce.model.client.Name;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.util.attributes.Tag;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditClientCommand.EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientCommand.EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientCommand.EditClientDescriptor descriptor) {
        this.descriptor = new EditClientCommand.EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientCommand.EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setTags(client.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditClientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditClientCommand.EditClientDescriptor build() {
        return descriptor;
    }
}
