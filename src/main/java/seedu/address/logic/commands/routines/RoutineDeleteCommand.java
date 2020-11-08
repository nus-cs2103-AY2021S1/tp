package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.routine.Routine;

/**
 * Deletes a routine identified using it's displayed index from fitNUS.
 */
public class RoutineDeleteCommand extends Command {

    public static final String COMMAND_WORD = "routine_delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the routine identified by the index number used in the displayed routine list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ROUTINE_SUCCESS = "Deleted Routine: %1$s";

    private final Index targetIndex;

    /**
     * Creates a RoutineDeleteCommand to delete the specified {@code Index} of the routine
     */
    public RoutineDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Routine> lastShownList = model.getFilteredRoutineList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
        }

        Routine routineToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRoutine(routineToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((RoutineDeleteCommand) other).targetIndex)); // state check
    }
}
