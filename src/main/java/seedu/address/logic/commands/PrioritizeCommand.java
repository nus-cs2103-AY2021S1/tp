package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
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
 * Sets priority for an assignment identified using it's displayed index from ProductiveNus.
 */
public class PrioritizeCommand extends Command {

    public static final String COMMAND_WORD = "prioritize";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets LOW, MEDIUM or HIGH priority for the assignment identified by the index number "
            + "used in the displayed assignment list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PRIORITY + "PRIORITY LEVEL\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PRIORITY + Priority.LOW_PRIORITY;

    public static final String MESSAGE_PRIORITIZE_ASSIGNMENT_SUCCESS = "Set priority for Assignment: %1$s";

    private final Index targetIndex;
    private final Priority priority;

    /**
     * Constructs a PriorityCommand to set priority to the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to prioritise.
     * @param priority Priority to be tagged to the assignment.
     */
    public PrioritizeCommand(Index targetIndex, Priority priority) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToPrioritise = lastShownList.get(targetIndex.getZeroBased());

        Assignment prioritizedAssignment = createPrioritisedAssignment(assignmentToPrioritise, priority);

        model.setAssignment(assignmentToPrioritise, prioritizedAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_PRIORITIZE_ASSIGNMENT_SUCCESS, prioritizedAssignment));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToPrioritise}.
     */
    private static Assignment createPrioritisedAssignment(Assignment assignmentToPrioritize, Priority priority) {
        assert assignmentToPrioritize != null;

        Name updatedName = assignmentToPrioritize.getName();
        Deadline updatedDeadline = assignmentToPrioritize.getDeadline();
        ModuleCode updatedModuleCode = assignmentToPrioritize.getModuleCode();
        Remind updatedRemind = assignmentToPrioritize.getRemind();
        Schedule updatedSchedule = assignmentToPrioritize.getSchedule();
        Priority updatedPriority = priority;
        Done updatedDone = assignmentToPrioritize.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                updatedPriority, updatedDone);
    }
}
