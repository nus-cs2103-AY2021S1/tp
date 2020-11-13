package seedu.internhunter.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.model.internship.InternshipItem;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** Feedback message to show to the user after a command execution. */
    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should switch tab. */
    private final boolean isSwitchTab;

    /** The application should switch display. */
    private final boolean isSwitchDisplay;

    /** List of matching internships generated based on user's profile skills */
    private ObservableList<InternshipItem> matchingInternships = FXCollections.observableArrayList();

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit, boolean isSwitchTab,
            boolean isSwitchDisplay) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isSwitchTab = isSwitchTab;
        this.isSwitchDisplay = isSwitchDisplay;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, true);
    }

    /**
     * Retrieves the feedback of the command being executed.
     *
     * @return A string representing the feedback of the command being executed.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Retrieves a boolean value denoting whether a help command is being executed.
     *
     * @return A boolean value denoting whether a help command is being executed.
     */
    public boolean isShowHelp() {
        return isShowHelp;
    }

    /**
     * Retrieves a boolean value denoting whether an exit command is being executed.
     *
     * @return A boolean value denoting whether an exit command is being executed.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Retrieves a boolean value denoting whether a switch command is being executed.
     *
     * @return A boolean value denoting whether a switch command is being executed.
     */
    public boolean isSwitchTab() {
        return isSwitchTab;
    }

    /**
     * Retrieves a boolean value denoting whether a view command is being executed or if there is a need to switch
     * display.
     *
     * @return A boolean value denoting whether a view command is being executed or if there is a need to
     * switch display.
     */
    public boolean isSwitchDisplay() {
        return isSwitchDisplay;
    }

    /**
     * Returns true if the matching internships is empty.
     *
     * @return True if the matching internships list is empty.
     */
    public boolean isShowMatchingInternships() {
        return !matchingInternships.isEmpty();
    }

    /**
     * Sets the matching internships with {@code listOfInternships}.
     *
     * @param listOfInternships The list of internships with requirements that matches the user's skills.
     */
    public void setMatchingInternships(ObservableList<InternshipItem> listOfInternships) {
        matchingInternships = listOfInternships;
    }

    /**
     * Obtains the matching internships list.
     *
     * @return Matching internship list.
     */
    public ObservableList<InternshipItem> getMatchingInternships() {
        return matchingInternships;
    }

    /**
     * Returns true if the 2 CommandResult have the same fields.
     *
     * @param other Other object to compare to.
     * @return True if the other CommandResult object has the same fields as this one.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isSwitchTab == otherCommandResult.isSwitchTab
                && isSwitchDisplay == otherCommandResult.isSwitchDisplay
                && matchingInternships.equals(otherCommandResult.matchingInternships);
    }

    /**
     * Returns the hashcode of this CommandResult object, which is the hashcode of its fields.
     *
     * @return Hashcode of this CommandResult object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isSwitchTab, isSwitchDisplay, matchingInternships);
    }

}
