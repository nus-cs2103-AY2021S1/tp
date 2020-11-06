package seedu.address.logic.commands.modulelistcommands;

import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Stores the details of the zoom link to be added to a module and the lesson the link belongs to.
 */
public class ZoomDescriptor {

    /** ZoomLink object that contains the zoom link to be added. */
    private ZoomLink zoomLink;
    /** ModuleLesson object containing the details of the module lesson which the zoom link belongs to. */
    private ModuleLesson moduleLesson;

    public ZoomDescriptor() {}

    /**
     * Copy constructor.
     */
    public ZoomDescriptor(ZoomDescriptor toCopy) {
        setZoomLink(toCopy.getZoomLink());
        setModuleLesson(toCopy.getModuleLesson());
    }

    public void setZoomLink(ZoomLink zoomLink) {
        this.zoomLink = zoomLink;
    }

    public ZoomLink getZoomLink() {
        return this.zoomLink;
    }

    public void setModuleLesson(ModuleLesson moduleLesson) {
        this.moduleLesson = moduleLesson;
    }

    public ModuleLesson getModuleLesson() {
        return this.moduleLesson;
    }

    @Override
    public boolean equals(Object other) {

        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ZoomDescriptor)) {
            return false;
        }

        // state check
        ZoomDescriptor descriptor = (ZoomDescriptor) other;

        return getZoomLink().equals(descriptor.getZoomLink())
                && getModuleLesson().equals(descriptor.getModuleLesson());
    }

}
