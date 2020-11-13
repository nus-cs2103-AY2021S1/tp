package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.STAFF_LIST;
import static com.eva.commons.core.PanelState.STAFF_PROFILE;
import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static com.eva.commons.util.DateUtil.dateToString;
import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

/**
 * Deletes leave from the given staff according to the given date.
 */
public class DeleteLeaveCommand extends Command {
    public static final String COMMAND_WORD = "dell";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes specified leave of a staff identified by the index number and leave date "
            + "used in the displayed staff list\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_DATE + "10/10/2020\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Leave: %1$s from %2$s";
    public static final String MESSAGE_NO_RECORD = "This staff: %s "
            + "has not taken any leave during any period containing this date: %s";
    public static final String MESSAGE_WRONG_PANEL = "Please switch to staff list panel "
            + "via 'list s-' to delete leave of staff";

    private final Index targetIndex;
    private final LocalDate date;

    /**
     * Creates a DeleteLeaveCommand to delete leave according to the date supplied
     * from the {@code Staff} identified by the given index.
     */
    public DeleteLeaveCommand(Index targetIndex, LocalDate date) {
        requireAllNonNull(targetIndex, date);
        this.targetIndex = targetIndex;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();
        if (!panelState.equals(STAFF_LIST) && !panelState.equals(STAFF_PROFILE)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                    MESSAGE_WRONG_PANEL));
        }
        if (panelState.equals(STAFF_PROFILE)) {
            if (!model.getCurrentViewStaff().getIndex().equals(targetIndex)) {
                throw new CommandException("Please go to staff profile with keyed in index"
                        + "or staff list panel with 'list s-'");
            }
        }
        List<Staff> lastShownList = model.getFilteredStaffList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Staff target = lastShownList.get(targetIndex.getZeroBased());

        Optional<Leave> removedLeave = model.hasLeaveDate(target, date);

        try {
            if (removedLeave.isPresent()) {
                model.deleteStaffLeave(target, removedLeave.get());
                model.setStaff(target, target); // force refresh staff model to update the leave list.
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                if (panelState.equals(STAFF_LIST)) {
                    model.setStaff(target, target); // force refresh staff model to update the leave list.
                    model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                } else if (panelState.equals(STAFF_PROFILE)) {
                    model.setStaff(target, target); // force refresh staff model to update the leave list.
                    model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                    Staff staffToView = lastShownList.get(targetIndex.getZeroBased());
                    model.setCurrentViewStaff(new CurrentViewStaff(staffToView, targetIndex));
                }
                return new CommandResult(String.format(MESSAGE_SUCCESS, removedLeave.get(), target.getName()),
                        false, false, true);
            } else {
                throw new CommandException(String.format(MESSAGE_NO_RECORD, target.getName(), dateToString(date)));
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
