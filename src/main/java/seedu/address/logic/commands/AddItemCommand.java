package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;


/**
 * Adds a item to the item list.
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "addi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a item to the item list. "
            + "Parameters: "
            + PREFIX_ITEM_NAME + "NAME "
            + PREFIX_ITEM_QUANTITY + "QUANTITY "
            + PREFIX_ITEM_DESCRIPTION + "DESCRIPTION "
            + PREFIX_ITEM_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_ITEM_NAME + "banana "
            + PREFIX_ITEM_QUANTITY + "44 "
            + PREFIX_ITEM_DESCRIPTION + "edible banana "
            + PREFIX_ITEM_LOCATION + "Bob’s banana farm ";


    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the item list";

    private final Item itemToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public AddItemCommand(Item item) {
        requireNonNull(item);
        itemToAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(itemToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(itemToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, itemToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && itemToAdd.equals(((AddItemCommand) other).itemToAdd));
    }
}
