package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.Assignment;



public class ViewCommandResult extends CommandResult {

    private String textArea;
    private List<ZoomLink> zoomLinksToCopy;
    private List<ZoomLink> zoomLinks;
    private List<Assignment> assignments;
    private Module module;

    public ViewCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public void setZoomLinks(List<ZoomLink> zoomLinks) {
        this.zoomLinks = zoomLinks;
    }

    public void setZoomLinksToCopy(List<ZoomLink> zoomLinksToCopy) {
        this.zoomLinksToCopy = zoomLinksToCopy;
    }

    public String getTextArea() {
        return textArea;
    }

    public Module getModule() {
        return module;
    }

    public List<ZoomLink> getZoomLinks() {
        return zoomLinks;
    }

    public List<ZoomLink> getZoomLinksToCopy() {
        return zoomLinksToCopy;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
    public List<Assignment> getAssignments() {
        return assignments;
    }
}
