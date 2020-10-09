package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.item.NameIsExactlyPredicate;
import seedu.address.ui.View;

/**
 * Finds the first item that matches what is searched for, and displays it's details.
 * Keyword matching is case insensitive.
 */
public class ViewDetailsCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the first item that matches "
            + "what is searched for, and displays it's details.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + "Bob's bone";

    private final NameIsExactlyPredicate predicate;

    public ViewDetailsCommand(NameIsExactlyPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        boolean filteredItemListNotEmpty = model.getFilteredItemList().size() > 0;
        if (filteredItemListNotEmpty) {
            return new CommandResult("Displaying searched item", false, false, View.InventoryType.DETAILED_ITEM);
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_NO_ITEM_FOUND, predicate.getKeyword()),
                    false, false, View.InventoryType.UNCHANGED);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDetailsCommand // instanceof handles nulls
                && predicate.equals(((ViewDetailsCommand) other).predicate)); // state check
    }
}
