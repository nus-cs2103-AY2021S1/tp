package seedu.address.logic.commands.consumption;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consumption.Consumption;

/**
 * Deletes a recipe identified using it's displayed index from the consumption list.
 */
public class DeleteConsumptionCommand extends Command {

    public static final String COMMAND_WORD = "deleteC";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the food eaten identified by the index number used in the displayed consumption list.\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONSUMPTION_SUCCESS = "Deleted food eaten: %1$s";

    private final Index toDelete;

    public DeleteConsumptionCommand(Index toDelete) {
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consumption> lastShownList = model.getFilteredConsumptionList();

        if (toDelete.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONSUMPTION_DISPLAYED_INDEX);
        }

        Consumption consumptionToDelete = lastShownList.get(toDelete.getZeroBased());
        model.deleteConsumption(consumptionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CONSUMPTION_SUCCESS, consumptionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteConsumptionCommand // instanceof handles nulls
                && toDelete.equals(((DeleteConsumptionCommand) other).toDelete)); // state check
    }
}
