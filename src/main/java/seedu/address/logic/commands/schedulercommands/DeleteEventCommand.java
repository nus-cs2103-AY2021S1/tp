package seedu.address.logic.commands.schedulercommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Event identified by the index number used in the displayed Event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted event: %1$s";
    private final Index index;

    /**
     * Creates a DeleteEventCommand to execute by the LogicManager later on.
     * @param index index of event to be deleted.
     */
    public DeleteEventCommand(Index index) {
        assert (index != null);
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> filteredList = model.getFilteredEventList();
        if (this.index.getZeroBased() >= filteredList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        Event event = filteredList.get(this.index.getZeroBased());
        model.deleteEvent(event);
        model.commitEventList();
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, event));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteEventCommand) {
            return this.index.equals(((DeleteEventCommand) other).index);
        } else {
            return false;
        }
    }
}
