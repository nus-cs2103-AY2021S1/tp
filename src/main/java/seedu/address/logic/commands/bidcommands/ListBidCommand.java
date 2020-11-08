package seedu.address.logic.commands.bidcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;

public class ListBidCommand extends Command {

    public static final String COMMAND_WORD = "list-bid";

    public static final String MESSAGE_SUCCESS = "Displaying full bid list.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
        return new CommandResult(MESSAGE_SUCCESS).setEntity(EntityType.BID);
    }
}
