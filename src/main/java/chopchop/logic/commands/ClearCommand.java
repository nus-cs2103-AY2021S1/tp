package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.model.RecipeBook;
import chopchop.model.Model;

/**
 * Clears the recipe book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Recipe book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRecipeBook(new RecipeBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
