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
 * Removes done status for an assignment identified using it's displayed index from ProductiveNus.
 */
public class UndoneCommand extends NegateCommand {

    public static final String COMMAND_WORD_SUFFIX = "done";
    public static final String COMMAND_WORD = NegateCommand.COMMAND_WORD + COMMAND_WORD_SUFFIX;

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD + " INDEX (must be a positive integer)";

    public static final String MESSAGE_MARK_ASSIGNMENT_AS_UNDONE_SUCCESS = "Marks assignment as undone: %1$s";
    public static final String MESSAGE_ALREADY_UNDONE_ASSIGNMENT = "This assignment is not marked as done.";

    /**
     * Constructs an UndoneCommand to remove done status from the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to remove done status
     */
    public UndoneCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (getTargetIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToMarkAsUndone = lastShownList.get(getTargetIndex().getZeroBased());

        if (!assignmentToMarkAsUndone.isMarkedDone() && model.hasAssignment(assignmentToMarkAsUndone)) {
            throw new CommandException(MESSAGE_ALREADY_UNDONE_ASSIGNMENT);
        }

        assert(assignmentToMarkAsUndone.isMarkedDone());
        Assignment undoneAssignment = createUndoneAssignment(assignmentToMarkAsUndone);

        model.setAssignment(assignmentToMarkAsUndone, undoneAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_AS_UNDONE_SUCCESS, undoneAssignment));
    }

    /**
     * Creates and returns an {@code Assignment} with the details of {@code assignmentToMarkUndone}.
     */
    private static Assignment createUndoneAssignment(Assignment assignmentToMarkUndone) {
        assert assignmentToMarkUndone != null;

        Name updatedName = assignmentToMarkUndone.getName();
        Time updatedDeadline = assignmentToMarkUndone.getDeadline();
        ModuleCode updatedModuleCode = assignmentToMarkUndone.getModuleCode();
        Remind updatedRemind = assignmentToMarkUndone.getRemind();
        Schedule updatedSchedule = assignmentToMarkUndone.getSchedule();
        Priority priority = assignmentToMarkUndone.getPriority();
        Done updatedDone = new Done();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoneCommand // instanceof handles nulls
                && getTargetIndex().equals(((UndoneCommand) other).getTargetIndex())); // state check
    }
}
