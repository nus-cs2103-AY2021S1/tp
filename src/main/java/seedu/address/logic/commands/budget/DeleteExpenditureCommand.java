package seedu.address.logic.commands.budget;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.state.expenditureindex.ExpenditureIndex;

/**
 * Deletes an expenditure from a specific Budget.
 */
public class DeleteExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String SYNTAX = COMMAND_WORD + " INDEX";
    public static final String DESCRIPTION = "Deletes an expenditure by index.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: " + "INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "Deleted expenditure.";

    private final ExpenditureIndex toDelete;

    /**
     * Creates a DeleteExpenditureCommand to delete the specified {@code expenditure}
     * @param expenditure the expenditure to be deleted
     */
    public DeleteExpenditureCommand(ExpenditureIndex expenditure) {
        requireNonNull(expenditure);
        this.toDelete = expenditure;
    }

    /**
     * Executes the delete expenditure command
     * @param model {@code Model} which the command should operate on.
     * @return the result of the command with the success message
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isWithinRange(toDelete)) {
            throw new CommandException(Messages.EXPENDITURE_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        model.saveToHistory();
        model.deleteExpenditure(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENDITURE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExpenditureCommand // instanceof handles nulls
                && toDelete.equals(((DeleteExpenditureCommand) other).toDelete));
    }

}
