package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Model;

/**
 * Lists all modules in GradPad to the user.
 */
public class ListCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_LIST_SUCCESS);
    }
}
