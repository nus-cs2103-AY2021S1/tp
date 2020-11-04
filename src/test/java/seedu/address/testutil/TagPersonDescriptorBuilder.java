package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTagCommand.TagPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class TagPersonDescriptorBuilder {

    private TagPersonDescriptor descriptor;

    public TagPersonDescriptorBuilder() {
        descriptor = new TagPersonDescriptor();
    }

    public TagPersonDescriptorBuilder(TagPersonDescriptor descriptor) {
        this.descriptor = new TagPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public TagPersonDescriptorBuilder(Person person) {
        descriptor = new TagPersonDescriptor();
        descriptor.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public TagPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public TagPersonDescriptor build() {
        return descriptor;
    }
}
