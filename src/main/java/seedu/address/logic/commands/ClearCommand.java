package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ClientList;
import seedu.address.model.Model;

/**
 * Clears the client list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Client list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClientList(new ClientList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
