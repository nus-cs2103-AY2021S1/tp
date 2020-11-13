package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.APPLICANT_LIST;
import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;
import static com.eva.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static com.eva.logic.commands.EditCommand.createEditedPerson;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;


public class EditApplicantCommand extends Command {
    public static final String COMMAND_WORD = "edita";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]"
            + "[" + PREFIX_COMMENT + "COMMENT]"
            + "[" + PREFIX_INTERVIEW_DATE + "DATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";

    public static final String MESSAGE_WRONG_PANEL = "Please switch to applicant list panel "
            + "via 'list a-' to edit applicant";

    private final Index index;
    private final EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an edit applicant command
     * @param index
     * @param editPersonDescriptor
     */
    public EditApplicantCommand(Index index, EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();
        if (!panelState.equals(APPLICANT_LIST) && !panelState.equals(APPLICANT_PROFILE)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                    MESSAGE_WRONG_PANEL));
        }
        if (panelState.equals(APPLICANT_PROFILE)) {
            if (!model.getCurrentViewApplicant().getIndex().equals(index)) {
                throw new CommandException("Please go to applicant with keyed in index"
                        + " or applicant list panel with 'list a-'");
            }
        }
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Applicant personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasApplicant((Applicant) editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }


        model.setApplicant(personToEdit, (Applicant) editedPerson);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        if (panelState.equals(APPLICANT_PROFILE)) {
            Applicant applicantToView = lastShownList.get(index.getZeroBased());
            model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, index));
        }

        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedPerson),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same obj
                || (other instanceof EditApplicantCommand // instanceof handles nulls
                && index.equals(((EditApplicantCommand) other).index))
                && editPersonDescriptor.equals(((EditApplicantCommand) other).editPersonDescriptor);
    }
}
