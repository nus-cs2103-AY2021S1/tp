package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bug.Bug;
import seedu.address.model.bug.State;

public class MoveByStateCommand extends MoveCommand {
    private State tagetState;

    /**
     * @param index of the bug in the filtered bug list to edit
     * @param state details to edit the bug with
     */
    public MoveByStateCommand(Index index, State state, State tagetState) {
        super(index, state);
        this.tagetState = tagetState;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bug> lastShownList = model.getFilteredBugListByState(tagetState);

        return updateList(lastShownList, model);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MoveCommand)) {
            return false;
        }

        // state check
        MoveByStateCommand e = (MoveByStateCommand) other;
        return index.equals(e.index)
                   && state.equals(e.state)
                   && tagetState.equals(e.tagetState);
    }
}
