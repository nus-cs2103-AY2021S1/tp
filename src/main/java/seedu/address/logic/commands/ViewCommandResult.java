package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.grade.Assignment;
import seedu.address.ui.DisplayZoomLink;

public class ViewCommandResult extends CommandResult {

    private String textArea;
    private List<DisplayZoomLink> displayZoomLinks;
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

    public void setDisplayZoomLinks(List<DisplayZoomLink> displayZoomLinks) {
        this.displayZoomLinks = displayZoomLinks;
    }

    public String getTextArea() {
        return textArea;
    }

    public List<DisplayZoomLink> getDisplayZoomLinks() {
        return displayZoomLinks;
    }

    public Module getModule() {
        return module;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}
