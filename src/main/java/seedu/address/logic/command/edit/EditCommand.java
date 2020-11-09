package seedu.address.logic.command.edit;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits an object identified using it's displayed index from Wanderlust list.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final int MINIMUM_LENGTH = 3;
    public static final int INDEX_POSITION = 2;


    public static final String MESSAGE_USAGE = EditTravelPlanCommand.MESSAGE_FORMAT + "\n"
            + EditAccommodationCommand.MESSAGE_FORMAT + "\n"
            + EditActivityCommand.MESSAGE_FORMAT + "\n"
            + EditFriendCommand.MESSAGE_FORMAT;

    public static final String SPECIFY_INDEX = "Please specify a valid index";
    public static final String INVALID_PARAMETERS = "Parameters specified are invalid";

    public static final String MESSAGE_NOT_EDITED = "At least one parameter to edit must be provided.";


    protected final Index targetIndex;

    public EditCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditCommand) other).targetIndex)); // state check
    }
}
