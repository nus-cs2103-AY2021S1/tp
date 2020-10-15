package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    //private EditModuleDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        //descriptor = new EditModuleDescriptor();
    }

    //public EditPersonDescriptorBuilder(EditModuleDescriptor descriptor) {
    //this.descriptor = new EditModuleDescriptor(descriptor);
    //}

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Contact person) {
        //descriptor = new EditModuleDescriptor();
        //descriptor.setName(person.getName());
        //descriptor.setEmail(person.getEmail());
        //descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        //descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        //descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        //descriptor.setTags(tagSet);
        return this;
    }

    //public EditModuleDescriptor build() {
    //return descriptor;
    //}
}
