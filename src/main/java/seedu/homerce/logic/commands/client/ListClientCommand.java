package seedu.homerce.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.clientpanel.ClientListPanel;

/**
 * Lists all clients in homerce to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "listcli";

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS, ClientListPanel.TAB_NAME);
    }
}
