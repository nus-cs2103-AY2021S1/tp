package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.attributes.NameContainsKeywordsPredicate;
import chopchop.ui.DisplayNavigator;

/**
 * Finds and lists all recipes in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRecipeCommand extends Command {

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Constructs a command that finds the given recipe item.
     */
    public FindRecipeCommand(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadRecipePanel();
        }

        var sz = model.getFilteredRecipeList().size();
        return CommandResult.message("Found %d recipe%s", sz, sz == 1 ? "" : "s");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRecipeCommand // instanceof handles nulls
                && predicate.equals(((FindRecipeCommand) other).predicate)); // state check
    }

    @Override
    public String toString() {
        return String.format("FindRecipeCommand(keywords: %s)", this.predicate.getKeywords());
    }

    public static String getCommandString() {
        return "find recipe";
    }

    public static String getCommandHelp() {
        return "Finds recipes by searching for keywords in their names";
    }

    public static String getUserGuideSection() {
        return "find-recipes--findrecipe";
    }
}
