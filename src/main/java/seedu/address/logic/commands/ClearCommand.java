package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes everything from the Order.\n"
            + "Format: clear\n"
            + "Examples:\n"
            + "clear: clears all items on current order";
    public static final String MESSAGE_SUCCESS = "Order has been cleared!";
    public static final String MESSAGE_EMPTY_ORDER = "Order is still empty!";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getOrderManager());

        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }

        if (model.getOrderSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_ORDER);
        }
        model.clearOrder();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
