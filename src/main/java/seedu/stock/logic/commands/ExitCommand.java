package seedu.stock.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits Warenager.\n"
            + "Format "
            + COMMAND_WORD;

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Stock Book as requested ...";
    private static final Logger logger = LogsCenter.getLogger(ExitCommand.class);


    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Exiting Warenager");
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, null, false, false, null, false, null, false, true);
    }

}
