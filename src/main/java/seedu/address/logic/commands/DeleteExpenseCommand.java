package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Expense;

/**
 * Deletes an expense identified using it's displayed index from the expense book.
 */
public class DeleteExpenseCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the expense identified by the index number used in the displayed expense list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXPENSE_SUCCESS = "Deleted expense: \n%1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteExpenseCommand to delete an expense.
     * @param targetIndex of the expense to be deleted.
     */
    public DeleteExpenseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete expense command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in an expense deleted.
     * @throws CommandException Throws exception if the index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Expense> lastShownList = model.getFilteredExpenseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteExpense(expenseToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete));
    }

    /**
     * Checks if a command objects are equal.
     * @param other Another object.
     * @return Whether the 2 command objects are equal or not. Return true if they are equal and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExpenseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteExpenseCommand) other).targetIndex)); // state check
    }
}
