package seedu.address.logic.commands;

import java.util.List;

public class ViewCommandResult extends CommandResult {

    private String textArea;
    private List zoomLinksToCopy;
    private List zoomLinks;
    private List assignments;

    public ViewCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public void setZoomLinks(List zoomLinks) {
        this.zoomLinks = zoomLinks;
    }

    public void setZoomLinksToCopy(List zoomLinksToCopy) {
        this.zoomLinksToCopy = zoomLinksToCopy;
    }

    public String getTextArea() {
        return textArea;
    }

    public List getZoomLinks() {
        return zoomLinks;
    }

    public List getZoomLinksToCopy() {
        return zoomLinksToCopy;
    }

    public void setAssignments(List assignments) {
        this.assignments = assignments;
    }
    public List getAssignments() {
        return assignments;
    }
}
