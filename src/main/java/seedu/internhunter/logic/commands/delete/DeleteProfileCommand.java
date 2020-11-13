package seedu.internhunter.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import java.util.List;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Deletes the ProfileItem from the Profile list.
 */
public class DeleteProfileCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PROFILE_ALIAS
            + ": Deletes a "
            + PROFILE_ITEM_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters: INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + PROFILE_ALIAS + " 1\n";

    private final Index targetIndex;

    /**
     * Creates an DeleteProfileCommand to delete the specified profile item at the targetIndex.
     *
     * @param targetIndex Target index of the profile item to be deleted.
     */
    public DeleteProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the DeleteProfileCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TabName currentTabName = model.getTabName();
        List<ProfileItem> lastShownList = model.getFilteredProfileList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        ProfileItem profileToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProfileItem(profileToDelete);

        String deleteSuccessMessage = String.format(Messages.MESSAGE_DELETED_ITEM, PROFILE_NAME, profileToDelete);
        return getCommandResult(model, deleteSuccessMessage, currentTabName, TabName.PROFILE, targetIndex);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProfileCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProfileCommand) other).targetIndex)); // state check
    }
}
