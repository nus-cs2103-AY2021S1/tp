package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ViewModuleCommand extends Command {
    public static final String COMMAND_WORD = "viewMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all the modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEWING_MODULES_SUCCESS = "Viewing All Modules";

    public ViewModuleCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_VIEWING_MODULES_SUCCESS), false, false, true, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewModuleCommand); // instanceof handles nulls
    }
}
