package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.expense.CategoryContainsKeywordsPredicate;

/**
 * Finds and lists all expenses in expense book whose category contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListExpenseByCategoryCommand extends Command {

    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter all expenses whose category contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " entertainment";

    public static final String MESSAGE_SUCCESS = "Listed all expenses by Category";

    private final CategoryContainsKeywordsPredicate predicate;

    /**
     * Creates an ListExpenseByCategoryCommand to list the expenses by the categories.
     * @param predicate to list the expenses.
     */
    public ListExpenseByCategoryCommand(CategoryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the list expense by category command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which the expenses are listed by the category.
     */
    @Override
    public CommandResult execute(Model model) {
        assert predicate != null;
        requireNonNull(model);
        model.updateFilteredExpenseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()));
    }

    /**
     * Checks if a command objects are equal.
     * @param other Another object.
     * @return Whether the 2 command objects are equal or not. Return true if they are equal and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListExpenseByCategoryCommand // instanceof handles nulls
                && predicate.equals(((ListExpenseByCategoryCommand) other).predicate)); // state check
    }
}
