package com.eva.logic.commands;

import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import com.eva.commons.core.PanelState;
import com.eva.model.Model;

/**
 * Lists all persons in the eva database to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": brings up list view of all staffs or applicants.\n"
            + "parameters: -LIST_TYPE"
            + "Example: " + COMMAND_WORD + "-staff";

    public static final String MESSAGE_SUCCESS = "Listed all staffs";

    private final boolean isStaffList;

    public ListCommand(boolean isStaffList) {
        this.isStaffList = isStaffList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isStaffList) {
            model.setPanelState(PanelState.STAFF_LIST);
            model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
        } else {
            model.setPanelState(PanelState.APPLICANT_LIST);
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
