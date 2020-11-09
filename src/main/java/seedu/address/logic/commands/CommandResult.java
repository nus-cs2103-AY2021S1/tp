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

    /** Performance information should be shown to the user. */
    private final boolean showPerformance;

    /** Application should be switched to quiz mode. */
    private final boolean switchToQuiz;

    /** Application should be switched to flashcards mode. */
    private final boolean switchToFlashcards;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showPerformance, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showPerformance = showPerformance;
        this.exit = exit;
        this.switchToQuiz = false;
        this.switchToFlashcards = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields (2).
     */
    public CommandResult(String feedbackToUser, boolean isQuiz, boolean isExitQuiz, boolean showHelp,
                         boolean showPerformance, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showPerformance = showPerformance;
        this.exit = exit;
        this.switchToQuiz = isQuiz;
        this.switchToFlashcards = isExitQuiz;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowPerformance() {
        return showPerformance;
    }

    public boolean isSwitchToQuiz() {
        return switchToQuiz;
    }

    public boolean isSwitchToFlashcards() {
        return switchToFlashcards;
    }

    public boolean isExit() {
        return exit;
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
