package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.parser.parameter.OptionalParameter;
import jimmy.mcgymmy.logic.predicate.FoodContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.NameContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.TagContainsKeywordsPredicate;
import jimmy.mcgymmy.model.Model;

/**
 * Finds and lists all persons in mcgymmy whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String SHORT_DESCRIPTION = "Filter the displayed list by a given keyword.";

    private OptionalParameter<FoodContainsKeywordsPredicate> foodPredicateParameter = this.addOptionalParameter(
            "keyword",
            "",
            "keywords (case-insensitive).",
            "chicken beef mutton", (s) -> new FoodContainsKeywordsPredicate(Arrays.asList(s.split("\\s+")))
    );
    private OptionalParameter<NameContainsKeywordsPredicate> namePredicateParameter = this.addOptionalParameter(
            "name",
            "n",
            "Name of the Food",
            "Cereal", (s) -> new NameContainsKeywordsPredicate(Arrays.asList(s.split("\\s+")))
    );
    private OptionalParameter<TagContainsKeywordsPredicate> tagPredicateParameter = this.addOptionalParameter(
            "tag",
            "t",
            "Tag associated with the Food",
            "Lunch", (s) -> new TagContainsKeywordsPredicate(Arrays.asList(s.split("\\s+")))
    );

    void setParameters(OptionalParameter<FoodContainsKeywordsPredicate> foodPredicateParameter,
                       OptionalParameter<NameContainsKeywordsPredicate> namePredicateParameter,
                       OptionalParameter<TagContainsKeywordsPredicate> tagPredicateParameter) {
        this.foodPredicateParameter = foodPredicateParameter;
        this.namePredicateParameter = namePredicateParameter;
        this.tagPredicateParameter = tagPredicateParameter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FoodContainsKeywordsPredicate foodPredicate = foodPredicateParameter.getValue().orElse(null);
        NameContainsKeywordsPredicate namePredicate = namePredicateParameter.getValue().orElse(null);
        TagContainsKeywordsPredicate tagPredicate = tagPredicateParameter.getValue().orElse(null);
        model.updateFilteredFoodList(foodPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FOOD_LISTED_OVERVIEW, model.getFilteredFoodList().size()));
    }
}
