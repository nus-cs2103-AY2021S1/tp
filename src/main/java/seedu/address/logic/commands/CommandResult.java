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

    /** Ui should trigger update. */
    private final boolean triggerUpdate;

    /** The application should exit. */
    private final boolean exit;

    /** The view of the timeline window should be shown. */
    private final boolean showTimeline;

    /** Ui should trigger update in timeline window. */
    private final boolean triggerUpdateTimeline;

    /** Ui should set to light theme. */
    private final boolean triggerLightTheme;

    /** Ui should set to light theme. */
    private final boolean triggerDarkTheme;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean triggerUpdate,
                         boolean showTimeline, boolean triggerUpdateTimeline) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.triggerUpdate = triggerUpdate;
        this.showTimeline = showTimeline;
        this.triggerUpdateTimeline = triggerUpdateTimeline;
        this.triggerLightTheme = false;
        this.triggerDarkTheme = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code triggerLightTheme} and {@code triggerDarkTHeme},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean triggerLightTheme, boolean triggerDarkTheme) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.triggerUpdate = false;
        this.showTimeline = false;
        this.triggerUpdateTimeline = false;
        this.triggerLightTheme = triggerLightTheme;
        this.triggerDarkTheme = triggerDarkTheme;
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

    public boolean isTriggerUpdate() {
        return triggerUpdate;
    }

    public boolean isShowTimeline() {
        return showTimeline;
    }

    public boolean isTriggerUpdateTimeline() {
        return triggerUpdateTimeline;
    }
    public boolean isTriggerLightTheme() {
        return triggerLightTheme;
    }

    public boolean isTriggerDarkTheme() {
        return triggerDarkTheme;
    }


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
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
