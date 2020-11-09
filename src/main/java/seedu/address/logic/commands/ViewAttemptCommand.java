package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.quiz.Attempt;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewAttemptCommand extends PerformanceCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the attempt identified by the index number used in the displayed attempts list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_ATTEMPT_SUCCESS = "Viewing Attempt %1$s";

    private final Index targetIndex;

    public ViewAttemptCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public PerformanceCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attempt> lastShownList = model.getAttemptList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTEMPT_DISPLAYED_INDEX);
        }

        Attempt attemptToView = lastShownList.get(targetIndex.getZeroBased());
        model.showAttempt(attemptToView);
        return new PerformanceCommandResult(String.format(MESSAGE_VIEW_ATTEMPT_SUCCESS, targetIndex.getOneBased()),
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAttemptCommand // instanceof handles nulls
                && targetIndex.equals(((ViewAttemptCommand) other).targetIndex)); // state check
    }
}
