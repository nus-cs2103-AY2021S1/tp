package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.expense.DateMatchesPredicate;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.NameContainsKeywordsPredicate;
import seedu.address.model.expense.TagsMatchesPredicate;
//import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Finds and lists all expenses in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all expenses whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate namePredicate;
    private final DateMatchesPredicate datePredicate;
    private final TagsMatchesPredicate tagsPredicate;

    public FindCommand(NameContainsKeywordsPredicate namePredicate, DateMatchesPredicate datePredicate, TagsMatchesPredicate tagsPredicate) {
        this.namePredicate = namePredicate;
        this.datePredicate = datePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        if (this.namePredicate.isEmpty() && this.datePredicate.isEmpty() && this.tagsPredicate.isEmpty()) {
            model.updateFilteredExpenseList(x -> false);
        }
        Predicate<Expense> predicate = x -> true;
        if (!namePredicate.isEmpty()) {
            predicate = predicate.and(namePredicate);
        }
        if (!datePredicate.isEmpty()) {
            predicate = predicate.and(datePredicate);
        }
        if (!tagsPredicate.isEmpty()) {
            predicate = predicate.and(tagsPredicate);
        }
        model.updateFilteredExpenseList(predicate);
        /*
        requireNonNull(model);
        if (!namePredicate.isEmpty()) {
            model.updateFilteredExpenseList(namePredicate);
        }
        if (!datePredicate.isEmpty()) {
            model.updateFilteredExpenseList(datePredicate);
        }
        if (!tagsPredicate.isEmpty()) {
            model.updateFilteredExpenseList(tagsPredicate);
        }
         */
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && datePredicate.equals(((FindCommand) other).datePredicate)
                && tagsPredicate.equals(((FindCommand) other).tagsPredicate)); // state check
    }
}
