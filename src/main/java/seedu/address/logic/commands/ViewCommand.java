package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Module details have been displayed successfully!\n"
            + "%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a module in the module list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + "1";

    private int moduleID;
    /**
     * Creates and initialises a new ViewCommand object.
     *
     * @param moduleIndex Zero based index of the module in the list of modules.
     */
    public ViewCommand(int moduleIndex) {
        this.moduleID = moduleIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Module> lastShownList = model.getFilteredModuleList();

        if (moduleID >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToView = lastShownList.get(moduleID);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToView));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
