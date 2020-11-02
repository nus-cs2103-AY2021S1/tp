package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.STAFF_LIST;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;

/**
 * Executes the deletion of the specified staff.
 */
public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "dels";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the staff identified by the index number used in the displayed staff list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_WRONG_PANEL = "Please switch to staff list panel "
            + "via 'list s-' to delete staff";

    public static final String MESSAGE_DELETE_STAFF_SUCCESS = "Deleted Staff: %1$s";

    private final Index targetIndex;

    public DeleteStaffCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();
        if (!panelState.equals(STAFF_LIST)) {
            throw new CommandException(MESSAGE_WRONG_PANEL);
        }
        List<Staff> lastShownList = model.getFilteredStaffList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        // cannot cast
        Staff staffToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStaff(staffToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_STAFF_SUCCESS, staffToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same obj
                || (other instanceof DeleteStaffCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStaffCommand) other).targetIndex));
    }
}
