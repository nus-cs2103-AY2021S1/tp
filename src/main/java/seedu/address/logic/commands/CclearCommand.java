package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class CclearCommand extends Command {

    public static final String COMMAND_WORD = "cclear";

    public static final String MESSAGE_SUCCESS = "All contacts deleted";

    public static final String MESSAGE_FAIL = "Contact list is already empty";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Clears all the contacts from the list.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isEmptyPersonList()) {
            throw new CommandException(MESSAGE_FAIL);
        } else {
            model.clearContacts();
            return new CommandResult(MESSAGE_SUCCESS);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CclearCommand); // instanceof handles nulls
    }
}
