package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROUTINES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all routines in fitNUS to the user.
 */
public class RoutineListCommand extends Command {

    public static final String COMMAND_WORD = "routine_list";

    public static final String MESSAGE_SUCCESS = "Listed all routines";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RoutineListCommand;
    }
}
