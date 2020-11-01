package seedu.address.logic.commands.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearIngredientCommand extends Command {

    public static final String COMMAND_WORD = "clearF";

    public static final String MESSAGE_SUCCESS = "Fridge's ingredients has been cleared!";

    private static final Logger logger = Logger.getLogger("ClearIngredientLogger");


    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Clearing Fridge Ingredient .......");
        requireNonNull(model);
        model.clearIngredient();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
