package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Budget display information should be shown to the user.
     */
    private final boolean showBudgetDisplay;

    /**
     * Graph window should be shown to user.
     */
    private final boolean showGraph;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showBudgetDisplay,
                         boolean showGraph) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showBudgetDisplay = showBudgetDisplay;
        this.showGraph = showGraph;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, true, false);
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

    public boolean isShowBudgetDisplay() {
        return showBudgetDisplay;
    }

    public boolean isShowGraph() {
        return showGraph;
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
                && exit == otherCommandResult.exit
                && showBudgetDisplay == otherCommandResult.showBudgetDisplay
                && showGraph == otherCommandResult.showGraph;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showBudgetDisplay, showGraph);
    }

}
