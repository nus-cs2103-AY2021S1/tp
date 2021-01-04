package seedu.homerce.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.clientpanel.ClientListPanel;

/**
 * Adds a client to homerce.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "addcli";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to the homerce. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in Homerce.";

    private final Client toAdd;

    /**
     * Creates an AddClientCommand to add the specified {@code Client}
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ClientListPanel.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
