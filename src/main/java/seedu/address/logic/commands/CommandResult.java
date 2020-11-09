package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should switch panels to Itinerary Attraction */
    private final ToSwitchItineraryPanels switchToItineraryAttraction;

    public enum ToSwitchItineraryPanels { YES, NO, NIL }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         ToSwitchItineraryPanels switchToItineraryAttraction) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.switchToItineraryAttraction = switchToItineraryAttraction;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false,
                ToSwitchItineraryPanels.NIL);
    }

    public CommandResult(String feedbackToUser, ToSwitchItineraryPanels switchToItineraryAttraction) {
        this(feedbackToUser, false, false, switchToItineraryAttraction);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public ToSwitchItineraryPanels isSwitchToItineraryAttraction() {
        return switchToItineraryAttraction;
    }

    /**
     * Overrides equal method to properly compare between 2 command results
     * @param other command result to compare with
     * @return true or false
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
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
        // && switchToItineraryAttraction == otherCommandResult.switchToItineraryAttraction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, switchToItineraryAttraction);
    }

}
