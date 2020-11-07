package seedu.address.testutil.contact;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.contactlistcommands.EditContactCommand.EditContactDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditContactDescriptor objects.
 */
public class EditContactDescriptorBuilder {

    private EditContactDescriptor descriptor;

    public EditContactDescriptorBuilder() {
        descriptor = new EditContactDescriptor();
    }

    public EditContactDescriptorBuilder(EditContactDescriptor descriptor) {
        this.descriptor = new EditContactDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditContactDescriptor} with fields containing {@code contact}'s details
     */
    public EditContactDescriptorBuilder(Contact person) {
        descriptor = new EditContactDescriptor();
        descriptor.setName(person.getName());
        descriptor.setEmail(person.getEmail());
        if (person.getTelegram().isPresent()) {
            descriptor.setTelegram(person.getTelegram().get());
        }
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withName(String name) {
        descriptor.setName(new ContactName(name));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditContactDescriptor}
     * that we are building.
     */
    public EditContactDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditContactDescriptor build() {
        return descriptor;
    }

}
