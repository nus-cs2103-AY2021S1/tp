package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting IntelliBrary as requested ...";

    /**
     * Executes exit command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        assert model != null;
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
