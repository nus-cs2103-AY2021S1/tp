package seedu.address.logic.commands.main;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.state.budgetindex.BudgetIndex;

public class DeleteBudgetCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String SYNTAX = COMMAND_WORD + " INDEX";
    public static final String DESCRIPTION = "Deletes a budget by index.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_DELETE_BUDGET_SUCCESS = "Deleted budget.";

    private final BudgetIndex toDelete;

    /**
     * Creates a DeleteBudgetCommand to delete the specified {@code budget}
     * @param budget the budget to be deleted
     */
    public DeleteBudgetCommand(BudgetIndex budget) {
        requireNonNull(budget);
        this.toDelete = budget;
    }

    /**
     * Executes the delete budget command.
     * @param model {@code Model} which the command should operate on.
     * @return the command result along with a success message
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isWithinRange(toDelete)) {
            throw new CommandException(Messages.BUDGET_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        model.saveToHistory();
        model.deleteBudget(toDelete);
        return new CommandResult(MESSAGE_DELETE_BUDGET_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBudgetCommand // instanceof handles nulls
                && toDelete.equals(((DeleteBudgetCommand) other).toDelete));
    }

}
