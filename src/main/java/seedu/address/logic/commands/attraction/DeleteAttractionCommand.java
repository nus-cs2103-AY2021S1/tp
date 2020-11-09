package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;

/**
 * Deletes an attraction identified using it's displayed index from the attractions list in TrackPad.
 */
public class DeleteAttractionCommand extends Command {

    public static final String COMMAND_WORD = "delete-attraction";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the attraction identified by the index number used in the displayed attraction list.\n"
            + "Parameters: INDEX must be a number between 0 and 2147483648.\n"
            + "Example: " + COMMAND_WORD + " 1.";

    public static final String MESSAGE_DELETE_ATTRACTION_SUCCESS = "Deleted attraction: %1$s.";

    private final Index targetIndex;

    public DeleteAttractionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAttraction(attractionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ATTRACTION_SUCCESS, attractionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAttractionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAttractionCommand) other).targetIndex)); // state check
    }
}
