package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

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
    public static final String MESSAGE_ASSIGNMENTS_DUPLICATED_INDEX = "Duplicated indexes found.";

    private final Comparator<Index> indexComparator = (firstIndex, secondIndex) -> {
        int firstIndexValue = firstIndex.getZeroBased();
        int secondIndexValue = secondIndex.getZeroBased();
        return secondIndexValue - firstIndexValue; // sort by descending order
    };

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    private void checkForDuplicatedIndexes(List<Index> targetIndexes) throws CommandException {
        List<Integer> zeroBasedIndexes = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            int zeroBasedIndex = targetIndex.getZeroBased();
            zeroBasedIndexes.add(zeroBasedIndex);
        }

        long distinctIndexes = zeroBasedIndexes.stream().distinct().count();
        long lengthOfIndexesList = zeroBasedIndexes.size();
        boolean containsDuplicates = distinctIndexes < lengthOfIndexesList;

        if (containsDuplicates) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand
                            .MESSAGE_ASSIGNMENTS_DUPLICATED_INDEX));
        }
    }

    private void checkForInvalidIndexes(List<Index> targetIndexes, Model model) throws CommandException {
        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        if (targetIndexes.isEmpty()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
            }
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        List<Assignment> deletedAssignments = new ArrayList<>();

        targetIndexes.sort(indexComparator);

        checkForDuplicatedIndexes(targetIndexes);
        checkForInvalidIndexes(targetIndexes, model);

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
