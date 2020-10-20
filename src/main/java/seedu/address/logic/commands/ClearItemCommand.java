package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;

/**
 * Clears the item list.
 */
public class ClearItemCommand extends Command {

    public static final String COMMAND_WORD = "cleari";
    public static final String MESSAGE_SUCCESS = "Item list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemList(new ItemList());
        // clears locations as well
        model.setLocationList(new LocationList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
