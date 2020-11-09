package seedu.address.testutil.event;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.schedulercommands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.tag.Tag;

public class EditEventDescriptorBuilder {
    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setTime(event.getTime());
        descriptor.setTags(event.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new EventName(name));
        return this;
    }

    /**
     * Sets the {@code time} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDate(String time) {
        descriptor.setTime(EventUtil.makeEventTime(time));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEventDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
