package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.InventoryBook;
import seedu.address.model.Model;

/**
 * Clears the inventory book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Inventory book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInventoryBook(new InventoryBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
