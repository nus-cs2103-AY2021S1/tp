package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.item.NameMatchesKeywordsPredicate;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Finds and lists all items in item list whose name contains any of the keywords.
 * Keyword matching is case insensitive.
 */
public class FindItemCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " apple, banana, carrot";

    private final NameMatchesKeywordsPredicate predicate;

    public FindItemCommand(NameMatchesKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        int filteredItemListSize = model.getFilteredItemList().size();
        boolean filteredItemListNotEmpty = filteredItemListSize > 0;

        if (filteredItemListNotEmpty) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, filteredItemListSize),
                    false, false, DisplayedInventoryType.ITEMS);
        } else {
            return new CommandResult(Messages.MESSAGE_NO_ITEM_MATCH,
                    false, false, DisplayedInventoryType.ITEMS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindItemCommand // instanceof handles nulls
                && predicate.equals(((FindItemCommand) other).predicate)); // state check
    }
}
