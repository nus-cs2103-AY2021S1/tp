package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static chopchop.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import chopchop.model.Model;

/**
 * Lists all recipes in the recipe book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public abstract CommandResult execute(Model model);
}
