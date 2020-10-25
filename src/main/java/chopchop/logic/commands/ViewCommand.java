package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.ui.DisplayNavigator;

/**
 * Displays a recipe identified by the index number or its name from the recipe book.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the recipe identified by the name used in the displayed recipe list.\n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " chicken soup";

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

        var recipe = resolveRecipeReference(this.item, model);
        if (recipe.isError()) {
            return CommandResult.error(recipe.getError());
        }


        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadRecipeDisplay(recipe.getValue());
        }

        return CommandResult.message("Displaying recipe '%s'", recipe.getValue().getName());
    }
}
