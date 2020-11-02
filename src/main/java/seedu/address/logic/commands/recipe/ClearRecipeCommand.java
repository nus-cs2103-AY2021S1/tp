package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearRecipeCommand extends Command {

    public static final String COMMAND_WORD = "clearR";
    public static final String MESSAGE_SUCCESS = "Recipe has been cleared!";

    private static final Logger logger = Logger.getLogger("ClearRecipeLogger");

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.log(Level.INFO, "Clearing Recipes List .......");
        model.clearRecipe();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
