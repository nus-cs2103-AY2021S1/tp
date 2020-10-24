package seedu.address.testutil;

import seedu.address.logic.commands.modulelistcommands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setModuleName(module.getName());
        descriptor.setZoomLink(module.getLink());
    }

    /**
     * Sets the {@code Name} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setModuleName(new ModuleName(name));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withZoomLink(String zoomLink) {
        descriptor.setZoomLink(new ZoomLink(zoomLink));
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
