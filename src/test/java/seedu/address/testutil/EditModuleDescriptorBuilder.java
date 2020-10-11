package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.Name;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code Module}'s details
     */
    public EditModuleDescriptorBuilder(Module Module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setName(Module.getName());
        descriptor.setZoomLink(Module.getZoomLink());
    }

    /**
     * Sets the {@code Name} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withZoomLink(String zoomLink) {
        descriptor.setZoomLink(new ZoomLink(zoomLink));
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
