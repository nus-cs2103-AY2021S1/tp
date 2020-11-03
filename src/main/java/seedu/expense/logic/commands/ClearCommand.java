package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;

/**
 * Clears the expense book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Expense book has been cleared! ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpenseBook(new ExpenseBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
