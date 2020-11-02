package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;

/**
 * Views a staff or applicant identified using it's displayed index from the eva database.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Brings user to the profile card of the person identified by the index number "
            + "used in the displayed staff or applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing %1$s: %2$s";
    public static final String MESSAGE_CHANGE_PANEL =
            "Please enter the view command at either the staff or applicant lists!";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the command message.
     * Views Staff if the command is executed from staff list.
     * Views Applicant if the command is executed from applicant list.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();

        try {
            String viewing;
            if (panelState.equals(PanelState.STAFF_LIST)) {
                viewing = executeViewStaff(model).toString();
            } else if (panelState.equals(PanelState.APPLICANT_LIST)) {
                executeViewApplicant(model);
                viewing = executeViewApplicant(model).toString();
            } else {
                throw new CommandException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL, MESSAGE_CHANGE_PANEL));
            }
            return new CommandResult(
                    String.format(
                            MESSAGE_VIEW_PERSON_SUCCESS, model.getPanelState().toString(), viewing
                    ), false, false, true);
        } catch (CommandException ce) {
            throw new CommandException(ce.getMessage());
        }
    }

    /**
     * Executes the logic to view Staff and returns the staff being viewed.
     */
    public Staff executeViewStaff(Model model) throws CommandException {
        assert (model != null);
        List<Staff> lastShownList = model.getFilteredStaffList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Staff staffToView = lastShownList.get(targetIndex.getZeroBased());
        model.setPanelState(PanelState.STAFF_PROFILE);
        model.setCurrentViewStaff(new CurrentViewStaff(staffToView, targetIndex));
        return staffToView;
    }

    /**
     * Executes the logic to view Applicant and returns the Applicant being viewed.
     */
    public Applicant executeViewApplicant(Model model) throws CommandException {
        assert (model != null);
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Applicant applicantToView = lastShownList.get(targetIndex.getZeroBased());
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, targetIndex));
        return applicantToView;
    }
}
