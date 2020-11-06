package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Entry;
import chopchop.model.Model;
import chopchop.model.attributes.ExpiryDateOnOrBeforePredicate;
import chopchop.model.attributes.NameContainsKeywordsFilterPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;

/**
 * Filters and lists all ingredients in ingredient book that match all filtering criteria.
 * Keyword matching is case insensitive.
 */
public class FilterIngredientCommand extends Command {

    private final TagContainsKeywordsPredicate tagPredicates;
    private final NameContainsKeywordsFilterPredicate namePredicates;
    private final ExpiryDateOnOrBeforePredicate expPredicate;

    /**
     * Constructs a command that finds the given ingredient item.
     * @param expPredicate
     * @param tagPredicates
     */
    public FilterIngredientCommand(ExpiryDateOnOrBeforePredicate expPredicate,
                                   TagContainsKeywordsPredicate tagPredicates,
                                   NameContainsKeywordsFilterPredicate namePredicates) {
        this.tagPredicates = tagPredicates;
        this.expPredicate = expPredicate;
        this.namePredicates = namePredicates;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        Predicate<Entry> p = x -> true;
        /*if (expPredicate != null && tagPredicates != null && namePredicates != null) {
            p = expPredicate.and(tagPredicates).and(namePredicates);
        } else if (expPredicate != null && tagPredicates != null) {
            p = expPredicate.and(tagPredicates);
        } else if (expPredicate != null && namePredicates != null) {
            p = expPredicate.and(namePredicates);
        } else if (expPredicate != null) {
            p = expPredicate;
        } else if (tagPredicates != null) {
            p = tagPredicates;
        }*/
        List<Predicate> predicates = Arrays.asList(expPredicate, tagPredicates, namePredicates);
        p = predicates.stream().filter(x -> x != null).collect(Collectors.reducing(p, (x, y) -> x.and(y)));
        model.updateFilteredIngredientList(p);

        var sz = model.getFilteredIngredientList().size();
        return CommandResult.message("Found %d ingredient%s", sz, sz == 1 ? "" : "s")
            .showingIngredientList();
    }

    @Override
    public String toString() {
        return String.format("FilterIngredientCommand(...)");
    }


    public static String getCommandString() {
        return "filter ingredient";
    }

    public static String getCommandHelp() {
        return "Filters ingredients by one or more criteria (names, tags and expiry dates)";
    }
}
