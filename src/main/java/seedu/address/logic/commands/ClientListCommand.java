package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ClientListCommand extends Command {

    public static final String COMMAND_WORD = "client list";
    public static final String MESSAGE_SUCCESS = "Listed all clients";
    public static final String MESSAGE_SUCCESS_NO_CLIENTS = "No clients to list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        model.resetSortedFilteredClientListOrder();
        if (model.getSortedFilteredClientList().size() == 0) {
            return new CommandResult(MESSAGE_SUCCESS_NO_CLIENTS, true, false, false);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, true, false, false);
        }
    }

}
