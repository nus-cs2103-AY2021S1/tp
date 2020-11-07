package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.TagSearchPredicate;

/**
 * Finds and lists all food items in Food inventory whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Usage : " + COMMAND_WORD + " [d/DESCRIPTION [MORE_DESCRIPTIONS]...] "
            + "[e/EXPIRY_DATE] [p/PRIORITY] [t/TAG]…\u200B\n  "
            + "Example: " + COMMAND_WORD + " d/apple bread carrot e/30-09-2021 p/medium t/wholemeal t/fresh";


    private final Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate;
    private final Optional<PrioritySearchPredicate> priorityPredicate;
    private final Optional<ExpiryDateSearchPredicate> expiryDatePredicate;
    private final Optional<TagSearchPredicate> tagsPredicate;

    /**
     * @param descriptionPredicate An {@code Optional} of the description search query
     * @param priorityPredicate An {@code Optional} of the priority search query
     * @param expiryDatePredicate An {@code Optional} of the expiry date search query
     * @param tagsPredicate An {@code Optional} of the tag search query
     */
    public FindCommand(Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate,
                       Optional<PrioritySearchPredicate> priorityPredicate,
                       Optional<ExpiryDateSearchPredicate> expiryDatePredicate,
                       Optional<TagSearchPredicate> tagsPredicate) {
        assert descriptionPredicate != null : "Description predicate cannot be null.";
        assert priorityPredicate != null : "Priority predicate cannot be null.";
        assert expiryDatePredicate != null : "Expiry date predicate cannot be null.";
        assert tagsPredicate != null : "Tags predicate cannot be null.";

        this.descriptionPredicate = descriptionPredicate;
        this.priorityPredicate = priorityPredicate;
        this.expiryDatePredicate = expiryDatePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    private Predicate<Food> combinePredicates() {
        Predicate<Food> predicate = PREDICATE_SHOW_ALL_FOODS;

        if (descriptionPredicate.isPresent()) {
            predicate = predicate.and(descriptionPredicate.get());
        }

        if (priorityPredicate.isPresent()) {
            predicate = predicate.and(priorityPredicate.get());
        }

        if (expiryDatePredicate.isPresent()) {
            predicate = predicate.and(expiryDatePredicate.get());
        }

        if (tagsPredicate.isPresent()) {
            predicate = predicate.and(tagsPredicate.get());
        }

        return predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(combinePredicates());

        return new CommandResult(
                String.format(Messages.MESSAGE_FOODS_LISTED_OVERVIEW, model.getFilteredFoodList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && descriptionPredicate.equals(((FindCommand) other).descriptionPredicate)
                && priorityPredicate.equals(((FindCommand) other).priorityPredicate)
                && expiryDatePredicate.equals(((FindCommand) other).expiryDatePredicate)
                && tagsPredicate.equals(((FindCommand) other).tagsPredicate)); // state check
    }
}
