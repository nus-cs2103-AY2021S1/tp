package seedu.address.logic.commands.client;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteEntityCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;

/**
 * Deletes a client identified using it's displayed client ID in HairStyleX.
 */
public class DeleteClientCommand extends DeleteEntityCommand<ClientId> {

    public static final String COMMAND_WORD = "delete_client";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the Client ID used in the displayed client list.\n"
            + "Parameters: CLIENT_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";


    public DeleteClientCommand(ClientId targetId) {
        super(targetId);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetId.equals(((DeleteClientCommand) other).targetId)); // state check
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {
        Client clientToDelete = model.getClientById(targetId);
        if (clientToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_ID);
        }
        model.deleteClient(clientToDelete);
        return String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);
    }
}

