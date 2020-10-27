package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Entry;
import chopchop.model.Model;
import chopchop.model.attributes.ExpiryDateMatchesKeywordsPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;
import chopchop.ui.DisplayNavigator;

/**
 * Filters and lists all ingredients in ingredient book that match all filtering criteria.
 * Keyword matching is case insensitive.
 */
public class FilterIngredientCommand extends Command {

    public static final String COMMAND_WORD = "filter ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters and gets all ingredients whose tag(s) "
            + "contain any of the specified keywords (case-insensitive) and expire before the "
            + "earliest specified date (keywords) and displays them as a list with index numbers.\n"
            + "Parameters: /FIELD_NAME KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "/expiry 2020-12-12 /tag favourite /tag handmade /expiry 2020-11-07\n"
            + "Note: Tag names should be split into single words";

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

        Predicate<Entry> p = x -> true;
        if (expPredicate != null && tagPredicates != null) {
            p = expPredicate.and(tagPredicates);
        } else if (expPredicate != null) {
            p = expPredicate;
        } else if (tagPredicates != null) {
            p = tagPredicates;
        }
        model.updateFilteredIngredientList(p);

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadIngredientPanel();
        }

        var sz = model.getFilteredIngredientList().size();
        return CommandResult.message("Found %d ingredient%s", sz, sz == 1 ? "" : "s");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FilterIngredientCommand // instanceof handles nulls
            && tagPredicates.equals(((FilterIngredientCommand) other).tagPredicates)
            && expPredicate.equals(((FilterIngredientCommand) other).expPredicate)); // state check
    }


    public static String getCommandString() {
        return "filter ingredient";
    }
}
