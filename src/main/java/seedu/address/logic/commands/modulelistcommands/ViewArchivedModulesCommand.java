package seedu.address.logic.commands.modulelistcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a module identified using it's displayed index from the address book.
 */
public class ViewArchivedModulesCommand extends Command {
    public static final String COMMAND_WORD = "viewarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all archived modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEW_ARHIVED_MODULES_SUCCESS = "Viewing archived modules";

    public ViewArchivedModulesCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.displayArchivedModules();
        return new CommandResult(MESSAGE_VIEW_ARHIVED_MODULES_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand); // instanceof handles nulls
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
