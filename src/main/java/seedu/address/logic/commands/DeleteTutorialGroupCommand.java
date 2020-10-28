package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.List;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "deleteTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the tutorial group identified by the index number used in the displayed tutorial group list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTORIAL_SUCCESS = "Deleted Tutorial Group: %1$s";

    public static final String MESSAGE_NOT_IN_TUTORIAL_VIEW = "You are currently not in the Tutorial Group view. " +
        "Run listTG to go back to the tutorial group view.";

    private final Index targetIndex;

    public DeleteTutorialGroupCommand(Index targetIndex) {this.targetIndex = targetIndex;}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isInTutorialGroupView()) {
            throw new CommandException(MESSAGE_NOT_IN_TUTORIAL_VIEW);
        }

        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        TutorialGroup tutorialGroupToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorialGroup(tutorialGroupToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialGroupToDelete));
    }
}
