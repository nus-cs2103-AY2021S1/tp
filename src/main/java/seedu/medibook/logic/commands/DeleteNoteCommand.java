package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;

/**
 * Deletes a medical note identified using it's displayed index in the patient's profile from MediBook.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the medical note identified by the index number used in the displayed index in the"
            + " patient's profile page.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Medical Note: %1$s";
    public static final String MESSAGE_DELETE_NOTE_ON_LIST = "You can only delete a medical note from a patient "
            + "when you are viewing his/her patient profile. Access the patient profile before "
            + "deleting a medical note.";
    public static final String MESSAGE_USER_CANNOT_DELETE = "Current user cannot delete medical notes";
    public static final String MESSAGE_CANNOT_DELETE_OTHER_DOCTOR_NOTES = "Can't delete other doctor's medical notes";

    private final Logger logger = LogsCenter.getLogger(DeleteNoteCommand.class);

    private final Index targetIndex;

    /**
     * Creates an DeleteNoteCommand to delete the specified {@code MedicalNote}
     * @param targetIndex index of the medical note to be deleted.
     */
    public DeleteNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setShouldLoadMedicalNotes(false);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (patientOptional.isEmpty()) {
            throw new CommandException(MESSAGE_DELETE_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in context does not exist in model";

        int zeroBasedIndex = targetIndex.getZeroBased();

        if (zeroBasedIndex >= displayedPatient.getNumOfMedicalNotes()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        if (model.getActiveUser().isEmpty()) {
            throw new CommandException(MESSAGE_USER_CANNOT_DELETE);
        }

        Doctor activeUser = model.getActiveUser().get();
        MedicalNote noteToDelete = displayedPatient.getMedicalNoteAtIndex(zeroBasedIndex);

        if (noteToDelete.isAuthoredBy(activeUser)) {
            displayedPatient.deleteMedicalNoteAtIndex(zeroBasedIndex);
        } else {
            throw new CommandException(MESSAGE_CANNOT_DELETE_OTHER_DOCTOR_NOTES);
        }

        logger.info("----------------[PATIENT AND ORDER OF MEDICAL NOTES:][" + displayedPatient + "\n"
                + displayedPatient.getMedicalNoteList() + "]");

        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
