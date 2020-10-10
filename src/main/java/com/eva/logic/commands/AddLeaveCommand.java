package com.eva.logic.commands;

import static com.eva.logic.parser.CliSyntax.PREFIX_INDEX;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE_END;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE_START;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.Person;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

/**
 * Adds the given leave period to an existing staff member.
 */
public class AddLeaveCommand extends Command {
    public static final String COMMAND_WORD = "addl";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds leave taken to the record of the staff taking leave "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer) "
            + PREFIX_LEAVE_START + "LEAVE START DATE "
            + PREFIX_LEAVE_END + "LEAVE END DATE (optional)"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_INDEX + "1 "
            + PREFIX_LEAVE_START + "08/10/2020 "
            + PREFIX_LEAVE_END + "10/10/2020 ";

    public static final String MESSAGE_SUCCESS = "Leave recorded: %1$s took %2$s";
    public static final String MESSAGE_DUPLICATE_RECORD = "This leave record already exists for this staff member.";

    private final Index targetIndex;
    private final Leave toAdd;

    /**
     * Creates an AddLeaveCommand to add the given leave to the {@code Staff} identified by the given index.
     */
    public AddLeaveCommand(Index targetIndex, Leave leave) {
        requireNonNull(targetIndex);
        requireNonNull(leave);
        this.targetIndex = targetIndex;
        this.toAdd = leave;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Staff staffToTakeLeave = (Staff) lastShownList.get(targetIndex.getZeroBased());

        if (model.hasStaffLeave(staffToTakeLeave, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.addStaffLeave(staffToTakeLeave, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, staffToTakeLeave, toAdd));
    }
}
