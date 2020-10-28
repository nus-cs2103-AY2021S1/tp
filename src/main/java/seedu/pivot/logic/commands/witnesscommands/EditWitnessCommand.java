package seedu.pivot.logic.commands.witnesscommands;

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
import seedu.pivot.model.investigationcase.caseperson.Witness;

public class EditWitnessCommand extends EditPersonCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_WITNESS
            + ": Edits a person in the opened case in PIVOT.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_WITNESS + " 1 "
            + PREFIX_EMAIL + "newEmail@mail.com "
            + PREFIX_ADDRESS + "New Road Crescent\n\n";

    public static final String MESSAGE_EDIT_WITNESS_SUCCESS = "Edited Witness: %1$s";
    public static final String MESSAGE_DUPLICATE_WITNESS = "This witness already exists in the case.";

    private static final Logger logger = LogsCenter.getLogger(EditWitnessCommand.class);

    public EditWitnessCommand(Index caseIndex, Index personIndex, EditPersonDescriptor editPersonDescriptor) {
        super(caseIndex, personIndex, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Editing a witness in the opened case...");
        requireNonNull(model);

        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;
        assert(caseIndex.getZeroBased() < lastShownList.size()) : ASSERT_VALID_INDEX;

        Case caseToEdit = lastShownList.get(caseIndex.getZeroBased());
        List<Witness> editedWitnesses = caseToEdit.getWitnesses();

        if (personIndex.getZeroBased() >= editedWitnesses.size()) {
            throw new CommandException(UserMessages.MESSAGE_INVALID_WITNESS_DISPLAYED_INDEX);
        }

        Witness witnessToEdit = editedWitnesses.get(personIndex.getZeroBased());
        Witness editedWitness = createEditedPerson(witnessToEdit, editPersonDescriptor);

        if (editedWitnesses.contains(editedWitness)) {
            throw new CommandException(MESSAGE_DUPLICATE_WITNESS);
        }

        editedWitnesses.set(personIndex.getZeroBased(), editedWitness);
        Case editedCase = new Case(caseToEdit.getTitle(), caseToEdit.getDescription(), caseToEdit.getStatus(),
                caseToEdit.getDocuments(), caseToEdit.getSuspects(), caseToEdit.getVictims(), editedWitnesses,
                caseToEdit.getTags());

        model.setCase(caseToEdit, editedCase);
        model.commitPivot(String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness));

        return new CommandResult(String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness));
    }

    private Witness createEditedPerson(Witness witnessToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert witnessToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(witnessToEdit.getName());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(witnessToEdit.getGender());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(witnessToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(witnessToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(witnessToEdit.getAddress());

        return new Witness(updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress);
    }
}
