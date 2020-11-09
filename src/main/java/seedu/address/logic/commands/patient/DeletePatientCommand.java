package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

//@@author chiamyunqing
/**
 * Deletes a patient identified by the patient's name from the application.
 * If the patient resides in a room, the patient will be removed and the room will be set to unoccupied.
 */
public class DeletePatientCommand extends Command {

    public static final String COMMAND_WORD = "deletepatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the patient's name used in the displayed patient list.\n"
            + "Parameters: NAME (must match exactly with the name of the patient to be deleted from the patient list)\n"
            + "Example: " + COMMAND_WORD + " Mary Doe";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";

    private final Name nameOfPatientToDelete;

    /**
     * Creates a DeleteCommand to delete the patient with the name {@code String}.
     * @param nameOfPatientToDelete name in the filtered patient list to be deleted
     */
    public DeletePatientCommand(Name nameOfPatientToDelete) {
        requireNonNull(nameOfPatientToDelete);
        this.nameOfPatientToDelete = nameOfPatientToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Patient> patientToDelete = model.getPatientWithName(nameOfPatientToDelete);

        if (patientToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_NAME);
        }

        if (model.isPatientAssignedToRoom(nameOfPatientToDelete)) {
            model.removePatientFromRoom(nameOfPatientToDelete);
        }

        Patient deletedPatient = patientToDelete.get();
        model.deletePatient(deletedPatient);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, deletedPatient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePatientCommand // instanceof handles nulls
                && nameOfPatientToDelete.equals(((DeletePatientCommand) other).nameOfPatientToDelete)); // state check
    }
}
