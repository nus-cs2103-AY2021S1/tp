package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.item.NameIsExactlyPredicate;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Finds the first item that matches what is searched for, and displays its details,
 * as well as all recipes which can craft this item. Keyword matching is case insensitive.
 */
public class ViewDetailsCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the first item that matches "
            + "what is searched for, and displays its details, as well as all recipes which can craft this item.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + " Bob's bone";

    public static final String MESSAGE_SUCCESS = "Displaying searched item and recipes which can craft it";

    private final NameIsExactlyPredicate predicate;

    public ViewDetailsCommand(NameIsExactlyPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // show only the item which matches the predicate
        model.updateFilteredItemList(predicate);
        // item is found
        if (!model.getFilteredItemList().isEmpty()) {
            assert model.getFilteredItemList().size() == 1;
            String itemName = model.getFilteredItemList().stream().findFirst().get().getName();
            model.updateFilteredRecipeList(recipe -> recipe.getProductName().equals(itemName));
            return new CommandResult(MESSAGE_SUCCESS, false, false,
                    DisplayedInventoryType.DETAILED_ITEM);
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_NO_ITEM_FOUND, predicate.getKeyword()),
                    false, false, DisplayedInventoryType.UNCHANGED);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDetailsCommand // instanceof handles nulls
                && predicate.equals(((ViewDetailsCommand) other).predicate)); // state check
    }
}
