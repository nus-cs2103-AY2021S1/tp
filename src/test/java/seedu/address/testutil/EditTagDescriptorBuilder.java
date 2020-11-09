package seedu.address.testutil;

import seedu.address.logic.commands.tagcommands.TagEditCommand.EditTagDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * A utility class to help with building EditTagDescriptor objects.
 */
public class EditTagDescriptorBuilder {

    private EditTagDescriptor descriptor;

    public EditTagDescriptorBuilder() {
        descriptor = new EditTagDescriptor();
    }

    public EditTagDescriptorBuilder(EditTagDescriptor descriptor) {
        this.descriptor = new EditTagDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditTagDescriptorBuilder(Tag tag) {
        descriptor = new EditTagDescriptor();
        descriptor.setTagName(tag.getTagName());
    }

    /**
     * Sets the {@code Name} of the {@code EditTagDescriptor} that we are building.
     */
    public EditTagDescriptorBuilder withTagName(String tagName) {
        descriptor.setTagName(new TagName(tagName));
        return this;
    }

    public EditTagDescriptor build() {
        return descriptor;
    }
}
