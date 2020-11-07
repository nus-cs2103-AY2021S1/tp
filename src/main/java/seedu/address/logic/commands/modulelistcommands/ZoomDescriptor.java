package seedu.address.logic.commands.modulelistcommands;

import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Stores the details of a {@code ZoomLink} and the {@code ModuleLesson} the link is mapped to.
 */
public class ZoomDescriptor {

    /** ZoomLink object that encapsulates a zoom link. */
    private ZoomLink zoomLink;
    /** ModuleLesson object encapsulating the details of the module lesson which the zoom link is mapped to. */
    private ModuleLesson moduleLesson;

    /**
     * Creates and initialises a new ZoomDescriptor with none of the fields initialised.
     */
    public ZoomDescriptor() {}

    /**
     * Creates and initialises a new ZoomDescriptor encapsulating the zoom link and module lesson
     * specified in {@code toCopy}.
     *
     * @param toCopy ZoomDescriptor object that contains the zoom link and module lesson to be copied.
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
