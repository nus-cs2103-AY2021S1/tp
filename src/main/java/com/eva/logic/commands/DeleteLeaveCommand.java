package com.eva.logic.commands;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static com.eva.commons.util.DateUtil.dateToString;
import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

/**
 * Deletes leave from the given staff according to the given date.
 */
public class DeleteLeaveCommand extends Command {
    public static final String COMMAND_WORD = "deleteleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes specified leave of a staff identified by the index number and leave date "
            + "used in the displayed staff list\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_DATE + "10/10/2020"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Leave: %1$s from %2$s";
    public static final String MESSAGE_NO_RECORD = "This staff: %s "
            + "has not taken any leave during any period containing this date: %s";

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

        List<Staff> lastShownList = model.getFilteredStaffList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Staff target = lastShownList.get(targetIndex.getZeroBased());

        Optional<Leave> removedLeave = model.hasLeaveDate(target, date);

        if (removedLeave.isPresent()) {
            model.deleteStaffLeave(target, removedLeave.get());
            model.setStaff(target, target); // force refresh staff model to update the leave list.
            model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, removedLeave.get(), target.getName()));
        } else {
            throw new CommandException(String.format(MESSAGE_NO_RECORD, target.getName(), dateToString(date)));
        }
    }
}
