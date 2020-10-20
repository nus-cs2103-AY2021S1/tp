package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.function.Predicate;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.parser.parameter.OptionalParameter;
import jimmy.mcgymmy.logic.predicate.DatePredicate;
import jimmy.mcgymmy.logic.predicate.FoodContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.NameContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.TagContainsKeywordsPredicate;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.food.Food;

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
    private OptionalParameter<DatePredicate> datePredicateParameter = this.addOptionalParameter(
            "tag",
            "t",
            "Tag associated with the Food",
            "Lunch", (s) -> new DatePredicate(s)
    );

    void setParameters(OptionalParameter<FoodContainsKeywordsPredicate> foodPredicateParameter,
                       OptionalParameter<NameContainsKeywordsPredicate> namePredicateParameter,
                       OptionalParameter<TagContainsKeywordsPredicate> tagPredicateParameter,
                       OptionalParameter<DatePredicate> datePredicateParameter) {
        this.foodPredicateParameter = foodPredicateParameter;
        this.namePredicateParameter = namePredicateParameter;
        this.tagPredicateParameter = tagPredicateParameter;
        this.datePredicateParameter = datePredicateParameter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FoodContainsKeywordsPredicate foodPredicate = foodPredicateParameter.getValue().orElse(null);
        NameContainsKeywordsPredicate namePredicate = namePredicateParameter.getValue().orElse(null);
        TagContainsKeywordsPredicate tagPredicate = tagPredicateParameter.getValue().orElse(null);
        DatePredicate datePredicate = datePredicateParameter.getValue().orElse(null);

        Predicate<Food> combinedPredicate = null;
        if (foodPredicate != null) {
            combinedPredicate = foodPredicate;
        }
        if (namePredicate != null) {
            if (combinedPredicate != null) {
                combinedPredicate = combinedPredicate.and(namePredicate);
            } else {
                combinedPredicate = namePredicate;
            }
        }
        if (tagPredicate != null) {
            if (combinedPredicate != null) {
                combinedPredicate = combinedPredicate.and(tagPredicate);
            } else {
                combinedPredicate = tagPredicate;
            }
        }
        if (datePredicate != null) {
            if (combinedPredicate != null) {
                combinedPredicate = combinedPredicate.and(datePredicate);
            } else {
                combinedPredicate = datePredicate;
            }
        }
        if (combinedPredicate == null) {
            combinedPredicate = food -> false;
        }
        model.updateFilteredFoodList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FOOD_LISTED_OVERVIEW, model.getFilteredFoodList().size()));
    }
}
