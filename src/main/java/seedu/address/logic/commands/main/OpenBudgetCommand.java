package seedu.address.logic.commands.main;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.MainPageCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.state.budgetindex.BudgetIndex;

/**
 * Opens a budget to view and modify expenditures contained within the budget.
 */
public class OpenBudgetCommand extends MainPageCommand {

    public static final String COMMAND_WORD = "open";
    public static final String SYNTAX = COMMAND_WORD + " INDEX";
    public static final String DESCRIPTION = "Opens a budget.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: " + "INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_SUCCESS = "Opened budget.";

    private final BudgetIndex toOpen;

    /**
     * Creates an OpenBudgetCommand to open the specified {@code budget}
     * @param toOpen the Index of the specific budget
     */
    public OpenBudgetCommand(BudgetIndex toOpen) {
        this.toOpen = toOpen;
    }

    /**
     * Executes the open budget command, sets the current page to the budget page.
     * @param model {@code Model} which the command should operate on.
     * @return the commmand result along with a success message
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isWithinRange(toOpen)) {
            throw new CommandException(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        model.openBudget(toOpen);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenBudgetCommand that = (OpenBudgetCommand) o;
        return Objects.equals(toOpen, that.toOpen);
    }
}
