package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.CurrentView;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Brings user to the profile card of the person identified by the index number "
            + "used in the displayed staff or applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing %1$s: %2$s";
    public static final String MESSAGE_VIEW_LIST_SUCCESS = "Viewing Person: %1$s";

    private final Index targetIndex;

    /**
     *
     * @param targetIndex
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Staff> lastShownList = model.getFilteredStaffList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Staff staffToView = lastShownList.get(targetIndex.getZeroBased());
        model.setPanelState(PanelState.STAFF_PROFILE);
        model.setCurrentView(new CurrentView<>(staffToView));

        return new CommandResult(
                String.format(
                        MESSAGE_VIEW_PERSON_SUCCESS, model.getPanelState().toString(), staffToView
                ), false, false, true);
    }
}
