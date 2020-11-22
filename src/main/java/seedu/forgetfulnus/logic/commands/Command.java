package seedu.forgetfulnus.logic.commands;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    enum CommandType {
        QUIZ_MODE, NOT_QUIZ_MODE, ANY_MODE;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Returns reminder message when the command is not applicable in the
     * current mode (quiz mode or out of quiz mode).
     *
     * @return reminder message that command is not applicable in current mode
     */
    public abstract String getQuizModeReminder();

    /**
     * Returns if the command can be used in quiz mode.
     *
     * @return QUIZ_MODE if command can only be used in quiz mode,
     *         NOT_QUIZ_MODE if command cannot be used in quiz mode,
     *         ANY_MODE if command can be used in either mode.
     */
    public abstract CommandType isQuizModeCommand();

    /**
     * Executes the command and returns the result message with quiz mode
     * checks.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeWithChecks(Model model) throws CommandException {
        if (isQuizModeCommand() == CommandType.ANY_MODE
                || (isQuizModeCommand() == CommandType.QUIZ_MODE && model.isQuizMode())
                || (isQuizModeCommand() == CommandType.NOT_QUIZ_MODE && !model.isQuizMode())) {
            return execute(model);
        } else {
            return new CommandResult(getQuizModeReminder());
        }
    }

}
