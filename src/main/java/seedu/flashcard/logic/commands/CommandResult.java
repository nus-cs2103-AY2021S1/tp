package seedu.flashcard.logic.commands;

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
     * The application enters review mode.
     */
    private final boolean reviewMode;

    /**
     * The flashcard index to view
     */
    private final Integer viewIndex;

    /**
     * The application should show answer for view command.
     */
    private final boolean showAnswer;

    /**
     * The application enters quiz mode
     */
    private final boolean quizMode;

    /**
     * The application should show statistics of the flashcard to the user.
     */
    private final boolean showStats;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean reviewMode, boolean quizMode) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.reviewMode = reviewMode;
        this.quizMode = quizMode;
        this.viewIndex = null;
        this.showAnswer = false;
        this.showStats = false;
    }

    /**
     * Constructs a {@code CommandResult} specifically for view commands.
     */
    public CommandResult(String feedbackToUser, Integer viewIndex, boolean showAnswer) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.reviewMode = false;
        this.quizMode = false;
        this.viewIndex = viewIndex;
        this.showAnswer = showAnswer;
        this.showStats = false;
    }

    /**
     * Constructs a {@code CommandResult} specifically for stats commands.
     */
    public CommandResult(String feedbackToUser, boolean showStats, Integer viewIndex) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.reviewMode = false;
        this.quizMode = false;
        this.viewIndex = viewIndex;
        this.showAnswer = false;
        this.showStats = showStats;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false,
                false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public Integer getViewIndex() {
        return viewIndex;
    }

    public boolean isReviewMode() {
        return reviewMode;
    }

    public boolean isShowAnswer() {
        return showAnswer;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isQuizMode() {
        return quizMode;
    }

    public boolean isShowStats() {
        return showStats;
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
                && reviewMode == otherCommandResult.reviewMode
                && quizMode == otherCommandResult.quizMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, reviewMode, quizMode);
    }

}
