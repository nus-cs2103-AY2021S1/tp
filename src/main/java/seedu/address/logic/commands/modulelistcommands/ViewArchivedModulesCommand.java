package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Encapsulates methods and information to display all modules in the archived module list to the user.
 */
public class ViewArchivedModulesCommand extends Command {
    public static final String COMMAND_WORD = "viewarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all archived modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEW_ARCHIVED_MODULES_SUCCESS = "Viewing archived modules";

    public ViewArchivedModulesCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.displayArchivedModules();
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_VIEW_ARCHIVED_MODULES_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand); // instanceof handles nulls
    }

}
