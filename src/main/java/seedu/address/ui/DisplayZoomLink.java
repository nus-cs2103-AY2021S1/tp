package seedu.address.ui;

import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Represents the module lesson name together with the module lesson zoom link.
 */
public class DisplayZoomLink {
    private ModuleLesson moduleLesson;
    private ZoomLink zoomLink;

    /**
     * Creates a {@code DisplayZoomLink} with the given {@code ModuleLesson} and {@code ZoomLink} to display.}
     *
     * @param moduleLesson the lesson in the module.
     * @param zoomLink the corresponding zoom link for the lesson.
     */
    public DisplayZoomLink(ModuleLesson moduleLesson, ZoomLink zoomLink) {
        this.moduleLesson = moduleLesson;
        this.zoomLink = zoomLink;
    }

    public ZoomLink getZoomLink() {
        return zoomLink;
    }

    public ModuleLesson getModuleLesson() {
        return moduleLesson;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DisplayZoomLink)) {
            return false;
        }

        // state check
        DisplayZoomLink displayZoomLink = (DisplayZoomLink) other;
        return moduleLesson.equals(displayZoomLink.getModuleLesson())
                && zoomLink.equals(displayZoomLink.getZoomLink());
    }
}
