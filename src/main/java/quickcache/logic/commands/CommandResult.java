package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final Feedback feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    private final boolean changeWindow;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean changeWindow) {
        requireNonNull(feedbackToUser);

        if ((showHelp && exit) || (showHelp && changeWindow) || (exit && changeWindow)) {
            throw new IllegalArgumentException("GUI cannot display more than one window change at a time");
        }

        this.feedbackToUser = new Feedback(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.changeWindow = changeWindow;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields including {@code question} and {@code isCorrect}.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean changeWindow,
                         Question question, Boolean isCorrect) {

        this(feedbackToUser, showHelp, exit, changeWindow);

        boolean isCorrectTrue = (isCorrect != null && isCorrect);
        if (isCorrectTrue && !changeWindow) {
            throw new IllegalArgumentException("GUI cannot display that answer is correct if window is not changed");
        }

        requireNonNull(question);
        this.feedbackToUser.setQuestion(question);
        this.feedbackToUser.setCorrect(isCorrect);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code question}, {@code isCorrect}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Question question, Boolean isCorrect, boolean changeWindow) {
        this(feedbackToUser, false, false, changeWindow, question, isCorrect);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code question}, {@code isCorrect}, {@code statistics}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Question question, Statistics statistics) {
        this(feedbackToUser, false, false, true, question, null);

        requireNonNull(statistics);
        this.feedbackToUser.setStatistics(statistics);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code statistics}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Statistics statistics) {
        this(feedbackToUser, false, false, true);

        requireNonNull(statistics);
        this.feedbackToUser.setStatistics(statistics);
    }

    public String getFeedbackToUser() {
        return feedbackToUser.toString();
    }

    public Feedback getFeedback() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isChangeWindow() {
        return changeWindow;
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
                && changeWindow == otherCommandResult.changeWindow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, changeWindow);
    }

}
