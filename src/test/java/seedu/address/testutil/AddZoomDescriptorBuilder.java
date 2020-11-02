package seedu.address.testutil;

import seedu.address.logic.commands.modulelistcommands.ZoomDescriptor;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * A utility class to help with building AddZoomDescriptor objects.
 */
public class AddZoomDescriptorBuilder {

    private ZoomDescriptor descriptor;

    public AddZoomDescriptorBuilder() {
        this.descriptor = new ZoomDescriptor();
    }

    public AddZoomDescriptorBuilder(ZoomDescriptor descriptor) {
        this.descriptor = new ZoomDescriptor(descriptor);
    }

    /**
     * Sets the {@code ZoomLink} of the {@code AddZoomDescriptor} that we are building.
     */
    public AddZoomDescriptorBuilder withZoomLink(String zoomLink) {
        descriptor.setZoomLink(new ZoomLink(zoomLink));
        return this;
    }

    /**
     * Sets the {@code ModuleLesson} of the {@code AddZoomDescriptor} that we are building.
     */
    public AddZoomDescriptorBuilder withModuleLesson(String lesson) {
        descriptor.setModuleLesson(new ModuleLesson(lesson));
        return this;
    }

    /**
     * Returns the newly built {@code AddZoomDescriptor} object.
     */
    public ZoomDescriptor build() {
        return descriptor;
    }

}

