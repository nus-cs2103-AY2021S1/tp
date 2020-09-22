package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.Model;
import seedu.address.model.item.Item;

/**
 * Adds a item to the inventory book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a item to the inventory book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_SUPPLIER + "SUPPLIER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Chicken "
            + PREFIX_QUANTITY + "12 "
            + PREFIX_SUPPLIER + "NTUC "
            + PREFIX_TAG + "meat ";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_ITEM_ADDED_TO_INVENTORY = "Item added to inventory. Stock is now: %1$s";

    private final Item toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public AddCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.hasItem(toAdd)) {
            Item toReplace = model.addOnExistingItem(toAdd);
            return new CommandResult(String.format(MESSAGE_ITEM_ADDED_TO_INVENTORY, toReplace));
        } else {
            model.addItem(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
