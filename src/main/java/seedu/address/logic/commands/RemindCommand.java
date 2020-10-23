package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;

/**
 * Sets reminders for an assignment identified using it's displayed index from the address book.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets reminders for the assignment identified by the index number "
            + "used in the displayed assignment list."
            + " Assignments with reminders set are permanently shown in the displayed reminders list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMIND_ASSIGNMENT_SUCCESS = "Set reminder for Assignment: %1$s";
    public static final String MESSAGE_REMINDED_ASSIGNMENT = "This assignment already has reminders set.";

    private final Index targetIndex;

    /**
     * Constructs a RemindCommand to set reminders to the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to remind
     */
    public RemindCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToRemind = lastShownList.get(targetIndex.getZeroBased());

        if (assignmentToRemind.isReminded() && model.hasAssignment(assignmentToRemind)) {
            throw new CommandException(MESSAGE_REMINDED_ASSIGNMENT);
        }

        assert(!assignmentToRemind.isReminded());
        Assignment remindedAssignment = createRemindedAssignment(assignmentToRemind);

        model.setAssignment(assignmentToRemind, remindedAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_REMIND_ASSIGNMENT_SUCCESS, remindedAssignment));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToRemind}.
     */
    private static Assignment createRemindedAssignment(Assignment assignmentToRemind) {
        assert assignmentToRemind != null;

        Name updatedName = assignmentToRemind.getName();
        Deadline updatedDeadline = assignmentToRemind.getDeadline();
        ModuleCode updatedModuleCode = assignmentToRemind.getModuleCode();
        Remind updatedRemind = assignmentToRemind.getRemind().setReminder();
        Schedule updatedSchedule = assignmentToRemind.getSchedule();
        Priority priority = assignmentToRemind.getPriority();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && targetIndex.equals(((RemindCommand) other).targetIndex)); // state check
    }
}
