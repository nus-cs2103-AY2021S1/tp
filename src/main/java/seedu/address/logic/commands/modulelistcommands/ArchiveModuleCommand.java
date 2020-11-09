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
 * Encapsulates methods and information to archive a module identified using it's displayed index
 * from the module list.
 */
public class ArchiveModuleCommand extends Command {
    public static final String COMMAND_WORD = "archivemodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_MODULE_SUCCESS = "Archived module: %1$s";

    public static final String MESSAGE_VIEW_ARCHIVED_MODULES_CONSTRAINT =
            "You are not currently viewing unarchived modules";

    private final Index targetIndex;

    /**
     * Creates and initialises a new ArchiveModuleCommand for the archiving of a module in the module list.
     *
     * @param targetIndex Index object encapsulating the index of the target module in the filtered displayed
     *                    module list.
     */
    public ArchiveModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        if (model.getModuleListDisplay()) {
            throw new CommandException(MESSAGE_VIEW_ARCHIVED_MODULES_CONSTRAINT);
        }
        Module moduleToArchive = lastShownList.get(targetIndex.getZeroBased());
        model.archiveModule(moduleToArchive);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_ARCHIVE_MODULE_SUCCESS, moduleToArchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveModuleCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveModuleCommand) other).targetIndex)); // state check
    }

}
