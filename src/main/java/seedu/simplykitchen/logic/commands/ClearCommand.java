package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;

/**
 * Clears the Food inventory.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Food inventory has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodInventory(new FoodInventory());
        model.commitFoodInventory();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
