package seedu.tasklist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.model.Model;
import seedu.tasklist.model.assignment.Assignment;

/**
 * Deletes a assignment identified using it's displayed index from the assignment list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assignment identified by the index number(s) used in the displayed assignment list.\n"
            + "Parameters: INDEX [MORE INDEXES] (must be a positive integer, "
            + "must not contain duplicates and cannot be greater than the size of the current "
            + "assignment list)\n"
            + "Examples: \n"
            + COMMAND_WORD + " 1\n"
            + COMMAND_WORD + " 1 2\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Assignment(s): %1$s";

    private final List<Index> targetIndexes;

    /**
     * Constructs a DeleteCommand to delete the specified assignment(s).
     *
     * @param targetIndexes indexes of assignments in the filtered assignment list to delete
     */
    public DeleteCommand(List<Index> targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        List<Assignment> deletedAssignments = new ArrayList<>();

        targetIndexes.sort(CommandLogic.INDEX_COMPARATOR);

        CommandLogic.checkForDuplicatedIndexes(targetIndexes);
        CommandLogic.checkForInvalidIndexes(targetIndexes, model, DeleteCommand.MESSAGE_USAGE);

        for (Index targetIndex : targetIndexes) {
            Assignment assignmentToDelete = lastShownList.get(targetIndex.getZeroBased());
            deletedAssignments.add(assignmentToDelete);
            model.deleteAssignment(assignmentToDelete);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedAssignments));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
