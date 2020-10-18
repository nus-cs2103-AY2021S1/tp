package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the Order.";
    public static final String MESSAGE_SUCCESS = "Order has been cleared!";
    public static final String MESSAGE_EMPTY_ORDER = "Order is still empty!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(model.getOrderManager());
        if (model.getOrderSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_ORDER);
        }
        model.clearOrder();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
