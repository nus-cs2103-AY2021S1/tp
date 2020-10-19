package seedu.address.logic.commands.itemcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.item.ItemContainsKeywordsPredicate;

/**
 * Finds and lists all items in inventory book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ItemFindCommand extends ItemCommand {

    public static final String COMMAND_WORD = "find-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "PREFIX only limited to name, supplier and tags\n"
            + "Parameters: " + "PREFIX " + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/chicken";

    private final ItemContainsKeywordsPredicate predicate;

    public ItemFindCommand(ItemContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireInventoryModel(model);
        InventoryModel inventoryModel = (InventoryModel) model;

        inventoryModel.updateFilteredItemList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, inventoryModel.getFilteredAndSortedItemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemFindCommand // instanceof handles nulls
                && predicate.equals(((ItemFindCommand) other).predicate)); // state check
    }
}
