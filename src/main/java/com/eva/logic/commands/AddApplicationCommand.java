package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.APPLICANT_LIST;
import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;
import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
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

public class AddApplicationCommand extends Command {
    public static final String COMMAND_WORD = "addapp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to an applicant. ";

    public static final String MESSAGE_USAGE_2 = COMMAND_WORD + ": Adds an application to an applicant. \n"
            + "Parameters: "
            + "INDEX FILEPATH \n"
            + "Example: " + COMMAND_WORD + " "
            + "1 data/resume.txt \n"
            + "or if you would like to use an in-built sample resume: " + COMMAND_WORD + " 1 sample";
    public static final String MESSAGE_WRONG_PANEL = "Please switch to applicant list panel "
            + "via 'list a-' to add application of applicant";


    public static final String MESSAGE_SUCCESS = "Application added to applicant: %1$s";
    public static final String MESSAGE_OVERRIDE = "Application overridden for applicant.";

    private final Application applicationToAdd;
    private final Index index;

    /**
     * Creates an AddApplicationCommand to add an application specified {@code Applicant}
     */
    public AddApplicationCommand(Index index, Application application) {
        requireAllNonNull(index, application);

        this.index = index;
        applicationToAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();
        if (!panelState.equals(APPLICANT_PROFILE) && !panelState.equals(APPLICANT_LIST)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                    MESSAGE_WRONG_PANEL));
        }
        if (panelState.equals(APPLICANT_PROFILE)) {
            if (!model.getCurrentViewApplicant().getIndex().equals(index)) {
                throw new CommandException("Please go to applicant with keyed in index"
                        + " or applicant list panel with 'list a-'");
            }
        }
        // if (model.hasApplication(toAdd)) // MESSAGE_OVERRIDE
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToUpdate = lastShownList.get(index.getZeroBased());
        model.addApplicantApplication(applicantToUpdate, applicationToAdd);
        if (panelState.equals(APPLICANT_LIST)) {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        } else if (panelState.equals(APPLICANT_PROFILE)) {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
            Applicant applicantToView = lastShownList.get(index.getZeroBased());
            model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, index));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, applicantToUpdate.getName()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicationCommand // instanceof handles nulls
                && applicationToAdd.equals(((AddApplicationCommand) other).applicationToAdd));
    }
}
