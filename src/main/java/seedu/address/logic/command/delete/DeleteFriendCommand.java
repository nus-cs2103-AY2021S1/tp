package seedu.address.logic.command.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.FRIEND_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.friend.Friend;

/**
 * Deletes a friend in a travel plan identified using the index from the travel plan.
 */
public class DeleteFriendCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "friend";

    public static final String MESSAGE_USAGE =
            "Delete a friend by its index in the displayed friend list using the format:\n"
            + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " INDEX\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FRIEND_SUCCESS = "Deleted Friend:\n%1$s";

    private final Index targetIndex;

    /**
     * Constructor for delete friend command.
     * @param targetIndex index to be deleted.
     */
    public DeleteFriendCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Friend> filteredFriendList = model.getFilteredFriendList();

        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();

        if (!isTravelPlan) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST);
        }

        if (targetIndex.getZeroBased() >= filteredFriendList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FRIEND_DISPLAYED_INDEX);
        }

        TravelPlanObject friendToDelete = filteredFriendList.get(targetIndex.getZeroBased());

        model.deleteTravelPlanObject(friendToDelete);
        assert !model.getFriendList().hasFriend((Friend) friendToDelete)
                : "Friend was not deleted!";
        return new CommandResult(String.format(MESSAGE_DELETE_FRIEND_SUCCESS, friendToDelete), FRIEND_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFriendCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteFriendCommand) other).targetIndex)); // state check
    }
}
