package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ScoreList;

/**
 * Clears the score list.
 */
public class ResetScoreCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String COMMAND_ELAB = "scores";
    public static final String MESSAGE_SUCCESS = "Scores have been cleared!";
    public static final String QUIZ_MODE_REMINDER = "'reset scores' command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";
    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setScoreList(new ScoreList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
