package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import static java.util.Objects.requireNonNull;

public class ListModuleCommand extends Command {
    public static final String COMMAND_WORD = "listMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all the modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEWING_MODULES_SUCCESS = "Viewing all modules";

    public static final String MESSAGE_NOT_IN_MODULE_VIEW = "You are currently not in the Module view. "
            + "Run listMod to go back to the Module view.";

    public ListModuleCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.setViewToModule();
        return new CommandResult(String.format(MESSAGE_VIEWING_MODULES_SUCCESS), false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListModuleCommand); // instanceof handles nulls
    }
}

