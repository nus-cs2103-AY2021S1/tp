package seedu.address.logic.commands.bidcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bid.Bid;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteBidCommand extends Command {

    public static final String COMMAND_WORD = "delete-bid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the bid identified by the "
            + "index number used in the displayed "
            + "bid list."
            + "\n\nParameters: \nINDEX (positive integer)\n"
            + "\n\nExample:\n" + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BID_SUCCESS = "Deleted Bid: %1$s";

    private final Index targetIndex;

    public DeleteBidCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bid> lastShownList = model.getFilteredBidList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
        }

        Bid bidToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBid(bidToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BID_SUCCESS, bidToDelete)).setEntity(EntityType.BID);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBidCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBidCommand) other).targetIndex)); // state check
    }
}
