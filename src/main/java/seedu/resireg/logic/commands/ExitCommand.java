package seedu.resireg.logic.commands;

import seedu.resireg.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = CommandWordEnum.EXIT_COMMAND.toString();

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting ResiReg as requested ...";

    public static final Help HELP = new Help(COMMAND_WORD, "Closes ResiReg.");

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
