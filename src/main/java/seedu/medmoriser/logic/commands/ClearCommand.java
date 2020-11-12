package seedu.medmoriser.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.medmoriser.commons.core.Messages;
import seedu.medmoriser.logic.commands.exceptions.CommandException;
import seedu.medmoriser.model.Medmoriser;
import seedu.medmoriser.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Medmoriser has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (QuizCommand.getIsQuiz()) {
            throw new CommandException(Messages.MESSAGE_ONGOING_QUIZ);
        } else {
            model.setMedmoriser(new Medmoriser());
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
