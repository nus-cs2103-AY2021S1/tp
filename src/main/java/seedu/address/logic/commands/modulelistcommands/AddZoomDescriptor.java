package seedu.address.logic.commands.modulelistcommands;

import seedu.address.model.module.ZoomLink;

/**
 * Stores the details of the zoom link to be added to a module.
 */
public class AddZoomDescriptor {

    /** ZoomLink object that contains the zoom link to be added. */
    private ZoomLink zoomLink;
    /** String containing the details of the module lesson which the zoom link belongs to. */
    private String moduleLessonType;

    public AddZoomDescriptor() {}

    /**
     * Copy constructor.
     */
    public AddZoomDescriptor(AddZoomDescriptor toCopy) {
        setLink(toCopy.getZoomLink());
        setModuleLessonType(toCopy.getModuleLessonType());
    }

    public void setLink(ZoomLink zoomLink) {
        this.zoomLink = zoomLink;
    }

    public ZoomLink getZoomLink() {
        return this.zoomLink;
    }

    public void setModuleLessonType(String moduleLessonType) {
        this.moduleLessonType = moduleLessonType;
    }

    public String getModuleLessonType() {
        return this.moduleLessonType;
    }

    @Override
    public boolean equals(Object other) {

        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddZoomDescriptor)) {
            return false;
        }

        // state check
        AddZoomDescriptor descriptor = (AddZoomDescriptor) other;

        return getZoomLink().equals(descriptor.getZoomLink())
                && getModuleLessonType().equals(descriptor.getModuleLessonType());
    }

}
