package seedu.address.logic.command.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Deletes an activity in a travel plan or wishlist identified using the index from the travel plan or wishlist.
 */
public class DeleteActivityCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "activity";

    public static final String MESSAGE_EXAMPLE = "Example: "
            + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE =
            "Delete an activity by its index in the displayed activity list using the format:\n"
            + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " INDEX\n"
            + MESSAGE_EXAMPLE;

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted Activity:\n%1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeleteActivityCommand.
     * @param targetIndex index to be deleted.
     */
    public DeleteActivityCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        List<Activity> activityList;
        if (isTravelPlan) {
            activityList = model.getFilteredActivityList();
        } else {
            activityList = model.getFilteredWishlist();
        }

        if (targetIndex.getZeroBased() >= activityList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        TravelPlanObject activityToDelete = activityList.get(targetIndex.getZeroBased());

        model.deleteTravelPlanObject(activityToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete), ACTIVITY_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteActivityCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteActivityCommand) other).targetIndex)); // state check
    }
}
