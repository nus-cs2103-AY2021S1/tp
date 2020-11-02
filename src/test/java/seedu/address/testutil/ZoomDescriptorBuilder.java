package seedu.address.testutil;

import seedu.address.logic.commands.modulelistcommands.ZoomDescriptor;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * A utility class to help with building ZoomDescriptor objects.
 */
public class ZoomDescriptorBuilder {

    private ZoomDescriptor descriptor;

    public ZoomDescriptorBuilder() {
        this.descriptor = new ZoomDescriptor();
    }

    public ZoomDescriptorBuilder(ZoomDescriptor descriptor) {
        this.descriptor = new ZoomDescriptor(descriptor);
    }

    /**
     * Sets the {@code ZoomLink} of the {@code ZoomDescriptor} that we are building.
     */
    public ZoomDescriptorBuilder withZoomLink(String zoomLink) {
        descriptor.setZoomLink(new ZoomLink(zoomLink));
        return this;
    }

    /**
     * Sets the {@code ModuleLesson} of the {@code ZoomDescriptor} that we are building.
     */
    public ZoomDescriptorBuilder withModuleLesson(String lesson) {
        descriptor.setModuleLesson(new ModuleLesson(lesson));
        return this;
    }

    /**
     * Returns the newly built {@code ZoomDescriptor} object.
     */
    public ZoomDescriptor build() {
        return descriptor;
    }

}

