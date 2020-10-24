package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.commons.core.Messages;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.attributes.IngredientsContainsKeywordsPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;
import chopchop.ui.DisplayNavigator;

/**
 * Finds and lists all ingredients in ingredient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterRecipeCommand extends Command {

    public static final String COMMAND_WORD = "filter recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose content contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " sugar";

    private final IngredientsContainsKeywordsPredicate ingredientPredicates;
    private final TagContainsKeywordsPredicate tagPredicates;

    /**
     * Constructs a command that filters and finds the matching recipe items.
     * @param indPredicates
     * @param tagPredicates
     */
    public FilterRecipeCommand(TagContainsKeywordsPredicate tagPredicates,
            IngredientsContainsKeywordsPredicate indPredicates) {
        this.tagPredicates = tagPredicates;
        this.ingredientPredicates = indPredicates;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        if (tagPredicates != null) {
            model.updateFilteredRecipeList(tagPredicates);
        }

        if (ingredientPredicates != null) {
            model.updateFilteredRecipeList(ingredientPredicates);
        }

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadRecipePanel();
        }

        return new CommandResult(String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW,
            model.getFilteredRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterRecipeCommand // instanceof handles nulls
                && tagPredicates.equals(((FilterRecipeCommand) other).tagPredicates)
                && ingredientPredicates.equals(((FilterRecipeCommand) other).ingredientPredicates)); // state check
    }

}
