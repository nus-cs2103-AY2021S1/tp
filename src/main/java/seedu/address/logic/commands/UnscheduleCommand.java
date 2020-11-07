package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;


/**
 * Removes the scheduler for an assignment identified using it's displayed index from ProductiveNus.
 */
public class UnscheduleCommand extends NegateCommand {

    public static final String COMMAND_WORD_SUFFIX = "schedule";
    public static final String COMMAND_WORD = NegateCommand.COMMAND_WORD + COMMAND_WORD_SUFFIX;

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD + " INDEX (must be a positive integer)";

    public static final String MESSAGE_UNSCHEDULE_ASSIGNMENT_SUCCESS = "Removed scheduler for Assignment: %1$s";
    public static final String MESSAGE_UNSCHEDULED_ASSIGNMENT = "This assignment does not have schedulers set.";

    /**
     * Constructs an UnscheduleCommand to remove reminders from the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to remove schedulers
     */
    public UnscheduleCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (getTargetIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToUnschedule = lastShownList.get(getTargetIndex().getZeroBased());

        if (!assignmentToUnschedule.getSchedule().isScheduled() && model.hasAssignment(assignmentToUnschedule)) {
            throw new CommandException(MESSAGE_UNSCHEDULED_ASSIGNMENT);
        }

        assert(assignmentToUnschedule.getSchedule().isScheduled());
        Assignment unscheduledAssignment = createUnscheduledAssignment(assignmentToUnschedule);

        model.setAssignment(assignmentToUnschedule, unscheduledAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_UNSCHEDULE_ASSIGNMENT_SUCCESS, unscheduledAssignment));
    }

    /**
     * Creates and returns an {@code Assignment} with the details of {@code assignmentToUnschedule}.
     */
    private static Assignment createUnscheduledAssignment(Assignment assignmentToUnschedule) {
        assert assignmentToUnschedule != null;

        Name updatedName = assignmentToUnschedule.getName();
        Time updatedDeadline = assignmentToUnschedule.getDeadline();
        ModuleCode updatedModuleCode = assignmentToUnschedule.getModuleCode();
        Remind updatedRemind = assignmentToUnschedule.getRemind();
        Schedule updatedSchedule = new Schedule();
        Priority updatedPriority = assignmentToUnschedule.getPriority();
        Done updatedDone = assignmentToUnschedule.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind,
                updatedSchedule, updatedPriority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnscheduleCommand // instanceof handles nulls
                && getTargetIndex().equals(((UnscheduleCommand) other).getTargetIndex())); // state check
    }
}
