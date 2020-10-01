package chopchop.logic.commands;

import chopchop.model.Model;

import static chopchop.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static java.util.Objects.requireNonNull;

/**
 * Lists all recipes in the recipe book to the user.
 */
public class ListIngredientCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all ingredients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
