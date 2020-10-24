package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.commons.core.Messages;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.attributes.ExpiryDateMatchesKeywordsPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;
import chopchop.ui.DisplayNavigator;

/**
 * Finds and lists all ingredients in ingredient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterIngredientCommand extends Command {

    public static final String COMMAND_WORD = "filter ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose content contain any of "
            + "the specified keywords (case-insensitive) as tags and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " /tag favourite /expiry 2020-04-05";

    private final TagContainsKeywordsPredicate tagPredicates;
    private final ExpiryDateMatchesKeywordsPredicate expPredicate;

    /**
     * Constructs a command that finds the given ingredient item.
     * @param expPredicate
     * @param tagPredicates
     */
    public FilterIngredientCommand(ExpiryDateMatchesKeywordsPredicate expPredicate,
            TagContainsKeywordsPredicate tagPredicates) {
        this.tagPredicates = tagPredicates;
        this.expPredicate = expPredicate;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        if (expPredicate != null) {
            model.updateFilteredIngredientList(expPredicate);
        }
        if (tagPredicates != null) {
            model.updateFilteredIngredientList(tagPredicates);
        }

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadIngredientPanel();
        }

        return new CommandResult(String.format(Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW,
            model.getFilteredIngredientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterIngredientCommand // instanceof handles nulls
                && tagPredicates.equals(((FilterIngredientCommand) other).tagPredicates)
                && expPredicate.equals(((FilterIngredientCommand) other).expPredicate)); // state check
    }

}
