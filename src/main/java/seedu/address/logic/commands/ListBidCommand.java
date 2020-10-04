package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDS;

public class ListBidCommand extends Command{

    public static final String COMMAND_WORD = "list-bid";

    public static final String MESSAGE_SUCCESS = "Listed all bids";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_BIDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
