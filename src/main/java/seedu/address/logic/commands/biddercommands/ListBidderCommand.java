package seedu.address.logic.commands.biddercommands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDDERS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListBidderCommand extends Command {

    public static final String COMMAND_WORD = "list-b";

    public static final String MESSAGE_SUCCESS = "Displaying full bidder list.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBidderList(PREDICATE_SHOW_ALL_BIDDERS);
        return new CommandResult(MESSAGE_SUCCESS).setEntity(EntityType.BIDDER);
    }
}
