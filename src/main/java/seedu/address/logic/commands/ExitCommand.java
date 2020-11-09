package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the application \n"
        + "Example: " + COMMAND_WORD + " (case sensitive, 'exit xx' is not allowed)";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Flashcard List as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ExitCommand;
    }

}
