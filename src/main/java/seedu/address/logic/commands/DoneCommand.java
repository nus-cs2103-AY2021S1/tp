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
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;

/**
 * Sets an assignment identified using it's displayed index from the address book as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the assignment identified by the index number "
            + "used in the displayed assignment list as done.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS = "Marks assignment as done: %1$s";
    public static final String MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE = "This assignment is already marked as done.";

    private final Index targetIndex;

    /**
     * Constructs a DoneCommand to mark the specified assignment as done.
     * @param targetIndex index of the assignment in the filtered assignment list to be marked done.
     */
    public DoneCommand(Index targetIndex) {
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

        Assignment assignmentToMarkDone = lastShownList.get(targetIndex.getZeroBased());

        if (assignmentToMarkDone.isMarkedDone() && model.hasAssignment(assignmentToMarkDone)) {
            throw new CommandException(MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
        }

        assert(!assignmentToMarkDone.isMarkedDone());
        Assignment assignmentMarkedDone = createAssignmentMarkedDone(assignmentToMarkDone);

        model.setAssignment(assignmentToMarkDone, assignmentMarkedDone);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS, assignmentMarkedDone));
    }

    /**
     * Creates and returns an {@code Assignment} with the details of {@code assignmentToMarkDone}.
     */
    private static Assignment createAssignmentMarkedDone(Assignment assignmentToMarkAsDone) {
        assert assignmentToMarkAsDone != null;

        Name updatedName = assignmentToMarkAsDone.getName();
        Deadline updatedDeadline = assignmentToMarkAsDone.getDeadline();
        ModuleCode updatedModuleCode = assignmentToMarkAsDone.getModuleCode();
        Remind updatedRemind = assignmentToMarkAsDone.getRemind();
        Schedule updatedSchedule = assignmentToMarkAsDone.getSchedule();
        Priority priority = assignmentToMarkAsDone.getPriority();
        // TODO: following method (and method in createRemindedCommand violates Law of Demeter.
        //  might have to think of another way to do this after this iteration
        Done done = assignmentToMarkAsDone.getDone().markAsDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority, done);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
