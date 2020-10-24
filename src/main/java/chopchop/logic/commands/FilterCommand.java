package chopchop.logic.commands;

import chopchop.commons.core.Messages;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.attributes.IngredientsContainsKeywordsPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;
import chopchop.model.recipe.Recipe;
import chopchop.ui.DisplayNavigator;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all ingredients in ingredient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FilterCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model, HistoryManager historyManager);

    @Override
    public abstract boolean equals(Object other);

}
