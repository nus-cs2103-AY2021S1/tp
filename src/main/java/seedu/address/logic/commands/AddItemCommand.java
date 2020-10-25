package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.ItemParserUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.parser.ItemParserUtil.DEFAULT_QUANTITY_TYPED;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;

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
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "banana "
            + PREFIX_ITEM_QUANTITY + "44 "
            + PREFIX_ITEM_DESCRIPTION + "edible banana "
            + PREFIX_ITEM_LOCATION + "Bob’s banana farm ";


    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the item list";
    public static final String MESSAGE_WARNING = "New item added: %1$s \nDo note that 1 or more fields"
            + " have been filled with default values,"
            + "\n please use edit to edit fields you wish to alter";

    private final ItemPrecursor itemPre;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public AddItemCommand(ItemPrecursor itemPrecursor) {
        requireNonNull(itemPrecursor);
        itemPre = itemPrecursor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Item itemToAdd;
        itemToAdd = model.processPrecursor(itemPre);

        if (model.hasItem(itemToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(itemToAdd);

        model.commitInventory();

        if (hasNonDefaultParams(itemToAdd)) {
            return new CommandResult(String.format(MESSAGE_WARNING, itemToAdd));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, itemToAdd));
    }

    /**
     * Checks if the item has any default parameters set
     * TODO expand this criteria
     * @param itemToAdd item to check
     * @return boolean to check
     */
    public static boolean hasNonDefaultParams(Item itemToAdd) {
        return itemToAdd.getQuantity().equals(DEFAULT_QUANTITY_TYPED)
                && (itemToAdd.getDescription().equals(DEFAULT_DESCRIPTION));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && itemPre.equals(((AddItemCommand) other).itemPre));
    }
}
