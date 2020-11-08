package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index or Nric from Hospify.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + " or deletes the patient by his NRIC number."
            + "Parameters: INDEX (must be a positive integer) or NRIC (e.g. S1234567A)\n"
            + "Example: " + COMMAND_WORD + " 3\n"
            + "Example: " + COMMAND_WORD + " S1234567A";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";

    private Optional<Index> targetIndex;
    private Optional<Nric> targetNric;

    /**
     * Initialize a DeleteCommand using index, set targetNric to empty Optional
     * @param targetIndex
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = Optional.ofNullable(targetIndex);
        this.targetNric = Optional.empty();
    }

    /**
     * Initialize a DeleteCommand using Nric, set targetIndex to empty Optional
     * @param targetNric Nric of patient
     */
    public DeleteCommand(Nric targetNric) {
        this.targetNric = Optional.ofNullable(targetNric);
        this.targetIndex = Optional.empty();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();
        Patient patientToDelete;

        if (!targetNric.isEmpty()) {
            lastShownList = lastShownList.stream()
                    .filter(patient -> patient.getNric().value.equals(targetNric.get().value.toUpperCase()))
                    .collect(Collectors.toList());
            if (lastShownList.size() != 1) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_NRIC);
            }
            patientToDelete = lastShownList.get(0);
        } else {
            if (targetIndex.get().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }
            patientToDelete = lastShownList.get(targetIndex.get().getZeroBased());
        }

        model.deletePatient(patientToDelete);
        assert !model.hasPatient(patientToDelete) : "Patient not deleted!";
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof DeleteCommand) {
            if ((!targetIndex.isEmpty()) && targetIndex.equals(((DeleteCommand) other).targetIndex)) {
                return true;
            }
            if ((!targetNric.isEmpty()) && targetNric.equals(((DeleteCommand) other).targetNric)) {
                return true;
            }
        }
        return false;
    }
}
