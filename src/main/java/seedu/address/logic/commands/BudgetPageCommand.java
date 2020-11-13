package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Class that represents general commands that occur in a budget page.
 */
public abstract class BudgetPageCommand extends Command {
    /**
     * Executes a command that occurs in the budget page.
     * @param model {@code Model} which the command should operate on.
     * @return the result executing the command
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;
}
