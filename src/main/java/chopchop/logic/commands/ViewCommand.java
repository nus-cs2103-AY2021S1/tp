package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.recipe.Recipe;
import chopchop.ui.DisplayNavigator;

/**
 * Display a recipe identified using its name from the recipe book.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the recipe identified by the name used in the displayed recipe list.\n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " chicken soup";

    public static final String MESSAGE_VIEW_SUCCESS = "Recipe: %s";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "No recipe named '%s'";

    private final ItemReference item;

    /**
     * Constructs a {@code ViewCommand} from the given recipe item.
     * @param item
     */
    public ViewCommand(ItemReference item) {
        requireNonNull(item);
        this.item = item;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        Recipe recipe = model
                .findRecipeWithName(this.item.getName())
                .orElseThrow(() -> new CommandException(String.format(MESSAGE_RECIPE_NOT_FOUND,
                        this.item.getName())));

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadRecipeDisplay(recipe);
        }

        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, recipe));
    }
}
