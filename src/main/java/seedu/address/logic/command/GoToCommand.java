package seedu.address.logic.command;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Navigates to the specific directory which can be either a travel plan or a directory.
 */
public class GoToCommand extends Command {
    public static final String COMMAND_WORD = "goto";
    public static final String TRAVEL_PLAN = "travelplan";
    public static final String WISHLIST = "wishlist";

    public static final String MESSAGE_USAGE = "Go to the wishlist directory using the following command:\n"
            + "goto -wishlist\n"
            + "Go to the travel plan directory identified by its index number used in the "
            + "displayed travel plan list using the following format:\n"
            + "goto -travelplan INDEX";

    public static final String MESSAGE_GOTO_SUCCESS = "goto directory: %1$s";

    public static final int WISHLIST_DIRECTORY = -1;
    public static final int COMMAND_TOKENS_TRAVELPLAN = 3;
    public static final int COMMAND_TOKENS_WISHLIST = 2;

    private final Index targetIndex;
    private final boolean isTravelPlan;

    /**
     * Constructor for GoToCommand (travel plan).
     * @param targetIndex index of travel plan to goto.
     */
    public GoToCommand(Index targetIndex, boolean isTravelPlan) {
        this.targetIndex = targetIndex;
        this.isTravelPlan = isTravelPlan;
    }

    /**
     * Constructor for GoToCommand (wishlist).
     */
    public GoToCommand(boolean isTravelPlan) {
        // TODO: change to this because test case in GoToCommandParserTest not passing
        //  (NullPointerException for .equals())
        this.targetIndex = Index.fromZeroBased(0);
        this.isTravelPlan = isTravelPlan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isTravelPlan) {
            List<TravelPlan> lastShownList = model.getFilteredTravelPlanList();

            assert targetIndex != null;
            int zeroBasedIndex = targetIndex.getZeroBased();

            if (zeroBasedIndex >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
            }

            TravelPlan travelPlanToGo = lastShownList.get(zeroBasedIndex);
            model.setDirectory(zeroBasedIndex);
            return new CommandResult(String.format(MESSAGE_GOTO_SUCCESS, TRAVEL_PLAN + " " + travelPlanToGo.getName()));
        } else {
            model.setDirectory(WISHLIST_DIRECTORY);
            return new CommandResult(String.format(MESSAGE_GOTO_SUCCESS, WISHLIST));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoToCommand // instanceof handles nulls
                && targetIndex.equals(((GoToCommand) other).targetIndex)) // state check
                && isTravelPlan == (((GoToCommand) other).isTravelPlan);
    }
}
