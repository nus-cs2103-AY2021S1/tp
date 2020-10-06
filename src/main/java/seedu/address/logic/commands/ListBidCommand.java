package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDS;

import seedu.address.model.Model;

public class ListBidCommand extends Command {

    public static final String COMMAND_WORD = "list-bid";

    public static final String MESSAGE_SUCCESS = "Listed all bids";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
