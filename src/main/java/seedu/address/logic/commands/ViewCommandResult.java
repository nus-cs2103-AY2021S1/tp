package seedu.address.logic.commands;

import java.util.List;

import seedu.address.ui.DisplayZoomLink;

public class ViewCommandResult extends CommandResult {

    private String textArea;
    private List<DisplayZoomLink> displayZoomLinks;

    public ViewCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public void setDisplayZoomLinks(List<DisplayZoomLink> displayZoomLinks) {
        this.displayZoomLinks = displayZoomLinks;
    }

    public String getTextArea() {
        return textArea;
    }

    public List<DisplayZoomLink> getDisplayZoomLinks() {
        return displayZoomLinks;
    }
}
