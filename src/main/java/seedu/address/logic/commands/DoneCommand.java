package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

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
 * Sets assignment(s) identified using it's displayed index(es) from the address book as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the assignment(s) identified by the index number(s) "
            + "used in the displayed assignment list as done.\n"
            + "Parameters: INDEX [MORE_INDEXES] (must be a positive integer, "
            + "must not contain duplicates and cannot be greater than the size of the current "
            + "assignment list)\n"
            + "Examples: \n"
            + COMMAND_WORD + " 1\n"
            + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS = "Marks assignment as done: %1$s";
    public static final String MESSAGE_MARK_ASSIGNMENTS_AS_DONE_SUCCESS = "Mark assignments as done: %1$s";
    // TODO: update description
    public static final String MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE = "This assignment is already marked as done.";

    private final List<Index> targetIndexes;

    /**
     * Constructs a DoneCommand to mark the specified assignment(s) as done.
     * @param targetIndexes index(es) of the assignment(s) in the filtered assignment list to be marked done.
     */
    public DoneCommand(List<Index> targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        List<Assignment> assignmentsToMarkDone = new ArrayList<>();

        targetIndexes.sort(CommandLogic.INDEX_COMPARATOR);

        CommandLogic.checkForDuplicatedIndexes(targetIndexes);
        CommandLogic.checkForInvalidIndexes(targetIndexes, model, DoneCommand.MESSAGE_USAGE);

        boolean isMultipleIndexes = targetIndexes.size() > 1;

        for (Index targetIndex : targetIndexes) {
            Assignment assignmentToMarkDone = lastShownList.get(targetIndex.getZeroBased());

            if (assignmentToMarkDone.isMarkedDone() && model.hasAssignment(assignmentToMarkDone)) {
                throw new CommandException(MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
            }

            assert(!assignmentToMarkDone.isMarkedDone());
            Assignment assignmentMarkedDone = createAssignmentMarkedDone(assignmentToMarkDone);

            model.setAssignment(assignmentToMarkDone, assignmentMarkedDone);
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
            assignmentsToMarkDone.add(assignmentToMarkDone);
        }

        return isMultipleIndexes
                ? new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENTS_AS_DONE_SUCCESS, assignmentsToMarkDone))
                : new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS, assignmentsToMarkDone));
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
                && targetIndexes.equals(((DoneCommand) other).targetIndexes)); // state check
    }
}
