package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.model.Model.PREDICATE_SHOW_ALL_BUDGETS;
import static seedu.expense.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import seedu.expense.model.Model;

/**
 * Lists all expenses in the expense book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all expenses. ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        model.updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
