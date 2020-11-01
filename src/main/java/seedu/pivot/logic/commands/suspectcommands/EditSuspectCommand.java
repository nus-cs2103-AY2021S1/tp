package seedu.pivot.logic.commands.suspectcommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_CASE_PAGE;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_VALID_INDEX;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.EditPersonCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Suspect;

public class EditSuspectCommand extends EditPersonCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_SUSPECT
            + ": Edits a person in the opened case in PIVOT.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_SUSPECT + " 1 "
            + PREFIX_EMAIL + "newEmail@mail.com "
            + PREFIX_ADDRESS + "New Road Crescent\n\n";

    public static final String MESSAGE_EDIT_SUSPECT_SUCCESS = "Edited Suspect: %1$s";
    public static final String MESSAGE_DUPLICATE_SUSPECT = "This suspect already exists in the case.";

    private static final Logger logger = LogsCenter.getLogger(EditSuspectCommand.class);

    public EditSuspectCommand(Index caseIndex, Index personIndex, EditPersonDescriptor editPersonDescriptor) {
        super(caseIndex, personIndex, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Editing a suspect in the opened case...");
        requireNonNull(model);

        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;
        assert(caseIndex.getZeroBased() < lastShownList.size()) : ASSERT_VALID_INDEX;

        Case caseToEdit = lastShownList.get(caseIndex.getZeroBased());
        List<Suspect> editedSuspects = caseToEdit.getSuspects();

        if (personIndex.getZeroBased() >= editedSuspects.size()) {
            throw new CommandException(UserMessages.MESSAGE_INVALID_SUSPECTS_DISPLAYED_INDEX);
        }

        Suspect suspectToEdit = editedSuspects.get(personIndex.getZeroBased());
        Suspect editedSuspect = createEditedPerson(suspectToEdit, editPersonDescriptor);

        if (editedSuspects.contains(editedSuspect)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUSPECT);
        }

        editedSuspects.set(personIndex.getZeroBased(), editedSuspect);
        Case editedCase = new Case(caseToEdit.getTitle(), caseToEdit.getDescription(), caseToEdit.getStatus(),
                caseToEdit.getDocuments(), editedSuspects, caseToEdit.getVictims(), caseToEdit.getWitnesses(),
                caseToEdit.getTags(), caseToEdit.getArchiveStatus());

        model.setCase(caseToEdit, editedCase);
        model.commitPivot(String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect), false);

        return new CommandResult(String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect));
    }

    private Suspect createEditedPerson(Suspect suspectToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert suspectToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(suspectToEdit.getName());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(suspectToEdit.getGender());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(suspectToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(suspectToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(suspectToEdit.getAddress());

        return new Suspect(updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress);
    }
}
