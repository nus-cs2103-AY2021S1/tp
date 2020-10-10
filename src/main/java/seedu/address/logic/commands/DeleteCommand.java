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
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + " or deletes the person by his Nric number."
            + "Parameters: INDEX (must be a positive integer) or NRIC (e.g. A00000001I)\n"
            + "Example: " + COMMAND_WORD + " 1"
            + "Example: " + COMMAND_WORD + " A00000001I";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

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
     * @param targetNric
     */
    public DeleteCommand(Nric targetNric) {
        this.targetNric = Optional.ofNullable(targetNric);
        this.targetIndex = Optional.empty();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();
        Patient patientToDelete;

        if (!targetNric.isEmpty()) {
            lastShownList = lastShownList.stream()
                    .filter(patient -> patient.getNric().equals(targetNric.get())).collect(Collectors.toList());
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

        model.deletePerson(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, patientToDelete));
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
