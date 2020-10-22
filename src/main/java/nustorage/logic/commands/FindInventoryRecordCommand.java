package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.commons.core.Messages;
import nustorage.model.Model;
import nustorage.model.record.InventoryRecordNameContainsKeywordsPredicate;

/**
 * Finds and lists all inventory records in the inventory whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindInventoryRecordCommand extends Command {

    public static final String COMMAND_WORD = "find_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all inventory records "
            + "which item names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " macbook iphone samsung";

    private final InventoryRecordNameContainsKeywordsPredicate predicate;

    public FindInventoryRecordCommand(InventoryRecordNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInventoryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INVENTORY_RECORDS_LISTED_OVERVIEW, model.getFilteredInventory().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInventoryRecordCommand // instanceof handles nulls
                && predicate.equals(((FindInventoryRecordCommand) other).predicate)); // state check
    }
}
