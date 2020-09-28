package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.flashcard.Question;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final Feedback feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        requireNonNull(feedbackToUser);
        this.feedbackToUser = new Feedback(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields including {@code question} and {@code isCorrect}.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, Question question, Boolean isCorrect) {
        requireNonNull(feedbackToUser);
        requireNonNull(question);
        requireNonNull(isCorrect);

        this.feedbackToUser = new Feedback(feedbackToUser);
        this.feedbackToUser.setQuestion(question);
        this.feedbackToUser.setCorrect(isCorrect);

        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code question}, {@code isCorrect}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Question question, Boolean isCorrect) {
        this(feedbackToUser, false, false, question, isCorrect);
    }

    public String getFeedbackToUser() {
        return feedbackToUser.toString();
    }

    public boolean isShowHelp() {
        return showHelp;
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
