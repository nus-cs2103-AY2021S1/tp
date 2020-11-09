package seedu.address.logic.command.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Deletes an accommodation in a travel plan identified using the index from the travel plan.
 */
public class DeleteAccommodationCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            "Delete an accommodation by its index in the displayed accommodation list using the format:\n"
            + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " INDEX\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + COMMAND_SEPARATOR + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOMMODATION_SUCCESS = "Deleted Accommodation:\n%1$s";

    private final Index targetIndex;

    /**
     * Constructor for Delete Accommodation Command
     * @param targetIndex index to be deleted
     */
    public DeleteAccommodationCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Accommodation> filteredAccommodationList = model.getFilteredAccommodationList();

        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();

        if (!isTravelPlan) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST);
        }

        if (targetIndex.getZeroBased() >= filteredAccommodationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
        }

        TravelPlanObject accommodationToDelete = filteredAccommodationList.get(targetIndex.getZeroBased());

        model.deleteTravelPlanObject(accommodationToDelete);
        assert !model.getAccommodationList().hasAccommodation((Accommodation) accommodationToDelete)
                : "Accommodation was not deleted!";
        return new CommandResult(String.format(MESSAGE_DELETE_ACCOMMODATION_SUCCESS, accommodationToDelete),
                ACCOMMODATION_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAccommodationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAccommodationCommand) other).targetIndex)); // state check
    }
}
