package seedu.homerce.logic.commands;

import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Homerce as requested ...";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
