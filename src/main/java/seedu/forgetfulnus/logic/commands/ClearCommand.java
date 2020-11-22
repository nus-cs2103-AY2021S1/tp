package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.Order;

/**
 * Clears the glossary.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Glossary has been cleared!";

    public static final String QUIZ_MODE_REMINDER = "'clear' command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGlossary(new Glossary());
        Order.setNextOrderOfAddition(1);
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
