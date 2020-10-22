package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Material;
import seedu.address.model.person.Module;

public class ViewTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "viewTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public ViewTutorialGroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = (List<Module>) model.getFilteredList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Module moduleToGetTGFrom = lastShownList.get(targetIndex.getZeroBased());
        model.changeToTutorialGroupList(moduleToGetTGFrom);
        return new CommandResult("SUCCESS");
        //return new CommandResult("NOT IMPLEMENTED YET");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTutorialGroupCommand // instanceof handles nulls
                && targetIndex.equals(((ViewTutorialGroupCommand) other).targetIndex)); // state check
    }
}
