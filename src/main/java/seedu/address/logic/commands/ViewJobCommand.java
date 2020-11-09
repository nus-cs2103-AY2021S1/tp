package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Displays a job on the right panel;
 */
public class ViewJobCommand extends Command {

    public static final String COMMAND_WORD = "view job";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the job identified by the index number used in the displayed job list.\n"
            + "Parameters: INDEX(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_PERSON_SUCCESS = "Displaying Job: %1$s";

    private final Index targetIndex;

    public ViewJobCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> displayableJobs = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= displayableJobs.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Job jobToDisplay = displayableJobs.get(targetIndex.getZeroBased());
        model.setDisplayedJob(jobToDisplay);
        return new CommandResult(String.format(MESSAGE_DISPLAY_PERSON_SUCCESS, jobToDisplay), false,
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewJobCommand // instanceof handles nulls
                && targetIndex.equals(((ViewJobCommand) other).targetIndex)); // state check
    }
}
