package seedu.stock.logic.commands;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     * @throws SourceCompanyNotFoundException If an error occurs during statistics command execution.
     * @throws SerialNumberNotFoundException If an error occurs due to serial number of stock not found.
     */
    public abstract CommandResult execute(Model model) throws CommandException,
            SourceCompanyNotFoundException, SerialNumberNotFoundException;

}
