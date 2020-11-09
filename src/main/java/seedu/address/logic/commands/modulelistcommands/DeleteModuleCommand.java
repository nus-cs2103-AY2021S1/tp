package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Encapsulates methods and information to delete a module identified using it's displayed index from the
 * module list.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "deletemodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted module: %1$s";

    private final Index targetIndex;

    /**
     * Creates and initialises a new DeleteModuleCommand for the deletion of a module in the module list.
     *
     * @param targetIndex Index object encapsulating the index of the target module in the filtered displayed
     *                    module list.
     */
    public DeleteModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        Module moduleToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (model.getModuleListDisplay()) {
            model.deleteArchivedModule(moduleToDelete);
        } else {
            model.deleteModule(moduleToDelete);
        }
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleCommand) other).targetIndex)); // state check
    }

}
