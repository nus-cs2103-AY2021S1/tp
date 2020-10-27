package seedu.address.logic.commands.biddercommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.bidder.Bidder;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteBidderCommand extends Command {

    public static final String COMMAND_WORD = "delete-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the bidder identified by the index number used in the displayed bidder list.\n"
            + "\n\nParameters: \nINDEX (must be a positive integer)\n"
            + "\n\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BIDDER_SUCCESS = "Deleted Bidder: %1$s";

    private final Index targetIndex;

    public DeleteBidderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bidder> lastShownList = model.getFilteredBidderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
        }

        Bidder bidderToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBidder(bidderToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BIDDER_SUCCESS, bidderToDelete))
                .setEntity(EntityType.BIDDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBidderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBidderCommand) other).targetIndex)); // state check
    }
}
