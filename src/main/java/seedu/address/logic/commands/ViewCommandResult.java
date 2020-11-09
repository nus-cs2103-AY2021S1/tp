package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.ui.DisplayZoomLink;

/**
 * Represents the result to be shown when running the view command.
 */
public class ViewCommandResult extends CommandResult {

    private String textArea;
    private List<DisplayZoomLink> displayZoomLinks;
    private List<Assignment> assignments;
    private Module module;

    /**
     * Creates a ViewCommandResult for GUI purposes for the View command.
     *
     * @param feedbackToUser the text to show to the user.
     */
    public ViewCommandResult(String feedbackToUser) {
        super(feedbackToUser);
        textArea = feedbackToUser;
        displayZoomLinks = new ArrayList<>();
        assignments = new ArrayList<>();
        module = new Module(new ModuleName("CS2103T"));
    }

    /**
     * Sets the module for the view command result.
     *
     * @param module the module to be viewed.
     */
    public void setModule(Module module) {
        requireNonNull(module);
        this.module = module;
    }

    /**
     * Sets the text to show for the view command result.
     *
     * @param textArea the text to be displayed.
     */
    public void setTextArea(String textArea) {
        requireNonNull(textArea);
        this.textArea = textArea;
    }

    /**
     * Sets the module lessons and zoomlinks to show for the view command result.
     *
     * @param displayZoomLinks the module lessons and zoomlinks to be displayed.
     */
    public void setDisplayZoomLinks(List<DisplayZoomLink> displayZoomLinks) {
        requireNonNull(displayZoomLinks);
        this.displayZoomLinks = displayZoomLinks;
    }

    /**
     * Returns the text to show for the view command result.
     */
    public String getTextArea() {
        return textArea;
    }

    /**
     * Returns the module lessons and zoomlinks to show for the view command result.
     */
    public List<DisplayZoomLink> getDisplayZoomLinks() {
        return displayZoomLinks;
    }

    /**
     * Returns the module be viewed for the view command result.
     */
    public Module getModule() {
        return module;
    }

    /**
     * Sets the assignments to show for the view command result.
     *
     * @param assignments the assignments to be displayed.
     */
    public void setAssignments(List<Assignment> assignments) {
        requireNonNull(assignments);
        this.assignments = assignments;
    }

    /**
     * Returns the assignments for the module to show for the view command result.
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommandResult) // instanceof handles nulls
                && textArea.equals(((ViewCommandResult) other).textArea)
                && displayZoomLinks.equals(((ViewCommandResult) other).displayZoomLinks)
                && assignments.equals(((ViewCommandResult) other).assignments)
                && module.equals(((ViewCommandResult) other).module);
    }
}
