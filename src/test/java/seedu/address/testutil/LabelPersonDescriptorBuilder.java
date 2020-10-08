package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddLabelCommand.LabelPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class LabelPersonDescriptorBuilder {

    private LabelPersonDescriptor descriptor;

    public LabelPersonDescriptorBuilder() {
        descriptor = new LabelPersonDescriptor();
    }

    public LabelPersonDescriptorBuilder(LabelPersonDescriptor descriptor) {
        this.descriptor = new LabelPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public LabelPersonDescriptorBuilder(Person person) {
        descriptor = new LabelPersonDescriptor();
        descriptor.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public LabelPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public LabelPersonDescriptor build() {
        return descriptor;
    }
}
