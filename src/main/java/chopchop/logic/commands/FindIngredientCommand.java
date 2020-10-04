package chopchop.logic.commands;

import chopchop.commons.core.Messages;
import chopchop.model.Model;
import chopchop.model.attributes.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all ingredients in ingredient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIngredientCommand extends FindCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose content contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " sugar";

    public FindIngredientCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INGREDIENT_LISTED_OVERVIEW, model.getFilteredIngredientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIngredientCommand // instanceof handles nulls
                && predicate.equals(((FindIngredientCommand) other).predicate)); // state check
    }

}
