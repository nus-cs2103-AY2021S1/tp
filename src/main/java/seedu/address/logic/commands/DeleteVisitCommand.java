package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DELETE;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;

public class DeleteVisitCommand extends Command {
    public static final String COMMAND_WORD = "deletevisit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the visit record of the person identified "
        + "by the index number used in the last person listing.\n "
        + COMMAND_WORD + "[PERSON INDEX]"
        + PREFIX_VISIT_DELETE + "[VISIT INDEX]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_VISIT_DELETE + "2";

    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Removed visit from Person: %1$s";
    public static final String MESSAGE_MISSING_INDEX_PROMPT = "Please specify index of report to be deleted\n"
        + "Usage: " + COMMAND_WORD + " [PERSON INDEX] "
        + PREFIX_VISIT_DELETE + "[REPORT INDEX]";;

    private static final int EMPTY_REPORT_INDICATOR = -1;

    private final Index index;
    private final int id;

    /**
     * @param index of the person in the filtered person list to edit the visitList
     * @param id of the report to be deleted
     */
    public DeleteVisitCommand(Index index, int id) {
        CollectionUtil.requireAllNonNull(index);

        this.index = index;
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient personToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPerson = personToEdit;
        if (id != EMPTY_REPORT_INDICATOR) {
            try {
                editedPerson = new Patient(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getIcNumber(),
                    personToEdit.getVisitHistory().deleteVisit(id), personToEdit.getAddress(), personToEdit.getEmail(),
                    personToEdit.getProfilePicture(),
                    personToEdit.getSex(), personToEdit.getBloodType(), personToEdit.getAllergies(),
                    personToEdit.getColorTag());

                model.setPatient(personToEdit, editedPerson);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_VISIT_INDEX);
            }
        } else {
            ObservableList<Visit> result = editedPerson.getVisitHistory().getObservableVisits();
            if (result.isEmpty()) {
                return new CommandResult("", result);
            } else {
                return new CommandResult(MESSAGE_MISSING_INDEX_PROMPT, result);
            }
        }
        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_VISIT_SUCCESS,
            personToEdit), editedPerson.getVisitHistory().getObservableVisits());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteVisitCommand)) {
            return false;
        }

        // state check
        DeleteVisitCommand e = (DeleteVisitCommand) other;
        return index.equals(e.index)
            && id == e.id;
    }
}
