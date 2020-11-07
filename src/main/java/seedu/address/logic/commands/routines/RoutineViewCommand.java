package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.routine.Routine;

/**
 * Views all exercises for a specified routine in fitNUS.
 */
public class RoutineViewCommand extends Command {

    public static final String COMMAND_WORD = "routine_view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a certain routine by index "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "Routine shown above:";
    public static final String MESSAGE_OUT_OF_BOUNDS_ROUTINE = "This routine index is out of bounds!";

    private final Index toView;

    /**
     * Creates a RoutineViewCommand to view all exercises for the specified {@code Index} of the routine
     */
    public RoutineViewCommand(Index index) {
        requireNonNull(index);
        toView = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Routine> lastShownList = model.getFilteredRoutineList();

        if (toView.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS_ROUTINE);
        }

        Routine routineToView = lastShownList.get(toView.getZeroBased());
        model.viewRoutine(routineToView);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineViewCommand
                && this.toView.equals(((RoutineViewCommand) other).toView)); // instanceof handles nulls
    }
}
