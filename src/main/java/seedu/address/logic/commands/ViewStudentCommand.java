package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class ViewStudentCommand extends Command {
    public static final String COMMAND_WORD = "viewStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the students identified by the index number used in the displayed tutorial group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing Students of Tutorial Group: %1$s";

    private final Index targetIndex;

    public ViewStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        TutorialGroup tutorialGroupToViewStudents = lastShownList.get(targetIndex.getZeroBased());
        model.setViewToStudent(tutorialGroupToViewStudents);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, tutorialGroupToViewStudents),
                false,
                false,
                false,
                true,
                false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStudentCommand // instanceof handles nulls
                && targetIndex.equals(((ViewStudentCommand) other).targetIndex)); // state check
    }
}
