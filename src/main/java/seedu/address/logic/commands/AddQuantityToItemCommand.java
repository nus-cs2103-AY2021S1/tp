package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;

/**
 * Adds a user-input quantity to an existing item.
 */
public class AddQuantityToItemCommand extends Command {

    public static final String COMMAND_WORD = "addq";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a quantity to an item "
            + "at the index number used in the displayed item list. "
            + "Existing quantity value will be modified by adding the input value.\n"
            + "Parameters: "
            + "[" + PREFIX_ITEM_NAME + "NAME] "
            + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY_TO_MODIFY_BY] "
            + "Example: " + COMMAND_WORD + PREFIX_ITEM_NAME + "Iron "
            + PREFIX_ITEM_QUANTITY + "20 ";

    public static final String MESSAGE_ITEM_NOT_PROVIDED = "Item name must be provided.";
    public static final String MESSAGE_QUANTITY_NOT_PROVIDED = "Quantity must be provided.";
    public static final String MESSAGE_NEGATIVE_QUANTITY = "Quantity will become negative.";

    private static final Logger logger = LogsCenter.getLogger(AddQuantityToItemCommand.class);

    private final String itemName;
    private final int quantity; // store as int to support negative

    /**
     * @param quantity quantity to add to the item, can be negative
     */
    public AddQuantityToItemCommand(String itemName, int quantity) {
        requireNonNull(itemName);

        this.itemName = itemName;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Item> itemList = new ArrayList<>(model.getFilteredItemList());

        // filter to only get matching and not deleted items
        itemList.removeIf(x -> !x.getName().equals(itemName));

        Item itemToEdit = itemList.stream()
                .findFirst() // Get the first (and only) item matching or else throw Error
                .orElseThrow(()-> new CommandException(String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, itemName)));
        assert(itemToEdit != null);
        assert(itemToEdit.getQuantity() != null);

        if ((itemToEdit.getQuantity().getNumber() + quantity) < 0) {
            throw new CommandException(MESSAGE_NEGATIVE_QUANTITY);
        }

        Quantity updatedQuantity = new Quantity(Integer.toString((itemToEdit.getQuantity().getNumber() + quantity)));
        assert updatedQuantity.getNumber() >= 0;

        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();

        editItemDescriptor.setName(itemToEdit.getName());
        editItemDescriptor.setDescription(itemToEdit.getDescription());
        editItemDescriptor.setQuantity(updatedQuantity);
        editItemDescriptor.setTags(itemToEdit.getTags());

        EditItemCommand editItemCommand = new EditItemCommand(itemName, editItemDescriptor);

        logger.info(itemToEdit.getName() + "'s quantity changed to " + updatedQuantity + ".");

        model.commitInventory();

        return editItemCommand.execute(model);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddQuantityToItemCommand)) {
            return false;
        }

        // check if itemName and quantity are the same
        AddQuantityToItemCommand a = (AddQuantityToItemCommand) other;
        return itemName.equals(a.itemName)
                && quantity == a.quantity;
    }
}
