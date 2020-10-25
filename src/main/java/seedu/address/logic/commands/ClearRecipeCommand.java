package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.RecipeList;

/**
 * Clears the recipe list.
 */
public class ClearRecipeCommand extends Command {

    public static final String COMMAND_WORD = "clearr";
    public static final String MESSAGE_SUCCESS = "Recipe list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRecipeList(new RecipeList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
