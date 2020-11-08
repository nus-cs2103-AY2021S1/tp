package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ProductiveNus;

/**
 * Clears ProductiveNus.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "ProductiveNus has been cleared!";

    private final String userInput;

    public ClearCommand(String userInput) {
        this.userInput = userInput;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean hasNoArgument = userInput.trim().contentEquals("clear");
        if (hasNoArgument) {
            model.setProductiveNus(new ProductiveNus());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
