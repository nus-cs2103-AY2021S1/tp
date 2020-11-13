package seedu.address.logic.commands.budget;

import seedu.address.logic.commands.BudgetPageCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Closes a particular budget so the user can return to the main screen.
 */
public class CloseBudgetCommand extends BudgetPageCommand {

    public static final String COMMAND_WORD = "close";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String DESCRIPTION = "Closes the current budget.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";

    public static final String MESSAGE_SUCCESS = "Closed budget.";

    /**
     * Executes the close budget command and sets the current page to be the Main Page.
     * @param model {@code Model} which the command should operate on.
     * @return the result of the command with the success message.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        model.closeBudget();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CloseBudgetCommand;
    }
}
