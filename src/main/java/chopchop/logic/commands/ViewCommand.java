package chopchop.logic.commands;

import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;

import static java.util.Objects.requireNonNull;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the recipe identified by the name used in the displayed recipe list.\n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " chicken soup";

    public static final String MESSAGE_VIEW_SUCCESS = "Recipe: %s";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "No recipe named '%s'";

    private final ItemReference item;

    public ViewCommand(ItemReference item) {
        requireNonNull(item);
        this.item = item;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        return new CommandResult("");
    }
}
