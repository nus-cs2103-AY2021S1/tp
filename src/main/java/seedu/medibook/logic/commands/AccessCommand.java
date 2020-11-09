package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Context;
import seedu.medibook.model.Model;
import seedu.medibook.model.patient.Patient;

/**
 * Accesses a patient's profile in the medi book.
 */
public class AccessCommand extends Command {

    public static final String COMMAND_WORD = "access";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Accesses the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ACCESS_PATIENT_SUCCESS = "Accessed Patient: %1$s";

    private final Index targetIndex;

    public AccessCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (model.getPatientToAccess().isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_IN_PATIENT_PROFILE);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToAccess = lastShownList.get(targetIndex.getZeroBased());
        Context context = model;
        context.accessPatient(patientToAccess);
        context.setShouldLoadMedicalNotes(true);

        return new CommandResult(String.format(MESSAGE_ACCESS_PATIENT_SUCCESS, patientToAccess), false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccessCommand // instanceof handles nulls
                && targetIndex.equals(((AccessCommand) other).targetIndex)); // state check
    }
}
