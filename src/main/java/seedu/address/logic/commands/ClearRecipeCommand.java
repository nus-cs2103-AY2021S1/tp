package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearRecipeCommand extends Command {

    public static final String COMMAND_WORD = "clearR";
    public static final String MESSAGE_SUCCESS = "Recipe has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearRecipe();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
