package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class ViewTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "viewTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the tutorial groups identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEWING_TG_SUCCESS = "Viewing tutorial groups of: %1$s";
    public static final String MESSAGE_NOT_IN_MODULE_VIEW = "You are currently not in the Module view. "
            + "Run listMod to go back to the Module view.";

    private final Index targetIndex;

    public ViewTutorialGroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInModuleView()) {
            throw new CommandException(MESSAGE_NOT_IN_MODULE_VIEW);
        }

        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToViewTutorialGroups = lastShownList.get(targetIndex.getZeroBased());
        model.setViewToTutorialGroup(moduleToViewTutorialGroups);
        return new CommandResult(String.format(MESSAGE_VIEWING_TG_SUCCESS, moduleToViewTutorialGroups),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTutorialGroupCommand // instanceof handles nulls
                && targetIndex.equals(((ViewTutorialGroupCommand) other).targetIndex)); // state check
    }
}
