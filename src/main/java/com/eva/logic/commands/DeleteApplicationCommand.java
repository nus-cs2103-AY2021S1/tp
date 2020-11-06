package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.APPLICANT_LIST;
import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;

public class DeleteApplicationCommand extends Command {
    public static final String COMMAND_WORD = "delapp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant's application identified by the index number used by the applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_WRONG_PANEL = "Please switch to applicant list panel "
            + "via 'list a-' to delete application";

    public static final String MESSAGE_DELETE_APPLICATION_SUCCESS = "Deleted Application from Applicant: %1$s";

    private final Index targetIndex;

    public DeleteApplicationCommand(Index targetIndex) {
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
        PanelState panelState = model.getPanelState();
        if (!panelState.equals(APPLICANT_LIST) && !panelState.equals(APPLICANT_PROFILE)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                    MESSAGE_WRONG_PANEL));
        }
        if (panelState.equals(APPLICANT_PROFILE)) {
            if (!model.getCurrentViewApplicant().getIndex().equals(targetIndex)) {
                throw new CommandException("Please go to applicant with keyed in index"
                        + " or applicant list panel with 'list a-'");
            }
        }
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant targetApplicant = lastShownList.get(targetIndex.getZeroBased());
        Application blankApplication = new Application();
        model.deleteApplication(targetApplicant, blankApplication);
        if (panelState.equals(APPLICANT_LIST)) {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        } else if (panelState.equals(APPLICANT_PROFILE)) {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
            Applicant applicantToView = lastShownList.get(targetIndex.getZeroBased());
            model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, targetIndex));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICATION_SUCCESS, targetApplicant),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicationCommand) other).targetIndex)); // state check
    }
}
