package seedu.expense.logic.commands;

import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.model.Model.PREDICATE_SHOW_ALL_BUDGETS;

import java.util.function.Predicate;

import seedu.expense.commons.core.Messages;
import seedu.expense.model.Model;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.expense.model.expense.Expense;

/**
 * Finds and lists all expenses in expense book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all the expenses with details "
            + "that match the arguments (keywords, date). "
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION + "KEYWORD] "
            + "[" + PREFIX_DATE + "DD-MM-YYYY]\n"
            + "Example: " + COMMAND_WORD + "  "
            + PREFIX_DATE + "18-02-2020 "
            + PREFIX_DESCRIPTION + "Lunch at YIH";

    private final DescriptionContainsKeywordsPredicate namePredicate;
    private final DateMatchesPredicate datePredicate;

    /**
     * Constructor that takes in the predicates used to filter through
     * the expenses list and find matching expenses. It matches based on keywords,
     * date, and tags.
     */
    public FindCommand(DescriptionContainsKeywordsPredicate namePredicate,
                       DateMatchesPredicate datePredicate) {
        this.namePredicate = namePredicate;
        this.datePredicate = datePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        if (this.namePredicate.isEmpty() && this.datePredicate.isEmpty()) {
            model.updateFilteredExpenseList(x -> false);
        }
        Predicate<Expense> predicate = x -> true;
        if (!namePredicate.isEmpty()) {
            predicate = predicate.and(namePredicate);
        }
        if (!datePredicate.isEmpty()) {
            predicate = predicate.and(datePredicate);
        }
        model.updateFilteredExpenseList(predicate);
        model.updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW,
                        model.getFilteredExpenseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && datePredicate.equals(((FindCommand) other).datePredicate)); // state check
    }
}
