package com.eva.logic.commands;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

/**
 * Adds the given leave period to an existing staff member.
 */
public class AddLeaveCommand extends Command {
    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds specified leave(s) taken to the record of the staff taking leave "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_LEAVE + PREFIX_DATE + "LEAVE START DATE "
            + PREFIX_DATE + "LEAVE END DATE (optional)"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + PREFIX_LEAVE + PREFIX_DATE + "08/10/2020 "
            + PREFIX_DATE + "10/10/2020 "
            + PREFIX_LEAVE + PREFIX_DATE + "20/10/2020 ";

    public static final String MESSAGE_SUCCESS = "Leave recorded: %1$s took %2$s";
    public static final String MESSAGE_DUPLICATE_RECORD = "This staff: %s "
            + "has overlapping leave date during this period: %s";

    private final Index targetIndex;
    private final List<Leave> toAdd;

    /**
     * Creates an AddLeaveCommand to add the given leave to the {@code Staff} identified by the given index.
     */
    public AddLeaveCommand(Index targetIndex, List<Leave> leave) {
        requireAllNonNull(targetIndex, leave);
        this.targetIndex = targetIndex;
        this.toAdd = leave;
    }
    // TODO checks for leave balance.
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Staff> lastShownList = model.getFilteredStaffList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Staff staffToTakeLeave = lastShownList.get(targetIndex.getZeroBased());
        StringBuilder sb = new StringBuilder();
        for (Leave leave : toAdd) {
            if (model.hasStaffLeave(staffToTakeLeave, leave)
                    || model.hasLeavePeriod(staffToTakeLeave, leave)) {
                throw new CommandException(
                        String.format(MESSAGE_DUPLICATE_RECORD, staffToTakeLeave.getName(), leave.toErrorMessage()));
            }
            model.addStaffLeave(staffToTakeLeave, leave);
            sb.append(leave.toString()).append(", ");
        }
        model.setStaff(staffToTakeLeave, staffToTakeLeave); //force update model to update leave list.
        model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, staffToTakeLeave.getName(), sb));
    }
}
