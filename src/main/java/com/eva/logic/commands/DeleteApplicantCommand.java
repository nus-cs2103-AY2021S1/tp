package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.applicant.Applicant;

/**
 * Executes the deletion of the specified applicant.
 */
public class DeleteApplicantCommand extends Command {
    public static final String COMMAND_WORD = "delapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted Applicant: %1$s";

    private final Index targetIndex;

    public DeleteApplicantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        // cannot cast
        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteApplicant(applicantToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same obj
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicantCommand) other).targetIndex));
    }
}
