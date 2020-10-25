package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DELETE;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

/**
 * Edits record of patient by index.
 */
public class EditVisitCommand extends Command {

    public static final String COMMAND_WORD = "editvisit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the visitation log of the patient identified "
            + "by the index number uused in the displayed patient list.\n"
            + "Parameters: " + "PATIENT_INDEX "
            + PREFIX_VISIT_DELETE + "VISIT_INDEX (both must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EDIT_VISIT + "2";

    public static final String MESSAGE_EDIT_VISIT_PROMPT = "Please refer to the popup window and edit visitation log.";
    public static final String MESSAGE_MISSING_INDEX_PROMPT = "Please specify the index of visitation to be edited\n"
            + "Usage: " + COMMAND_WORD + " [PERSON INDEX] "
            + PREFIX_EDIT_VISIT + "[REPORT INDEX]\n";

    private static final int EMPTY_VISIT_LOG = -1;

    private final Index patientIndex;
    private final int visitIndex;

    /**
     * @param patientIndex Patient's index
     * @param visitIndex Visit's index
     */
    public EditVisitCommand(Index patientIndex, int visitIndex) {
        requireAllNonNull(patientIndex);

        this.patientIndex = patientIndex;
        this.visitIndex = visitIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();
        int listSize = lastShownList.size();

        if (patientIndex.getZeroBased() >= listSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        Patient patientEdited = patientToEdit;
        VisitHistory visitHistory = patientEdited.getVisitHistory();
        ObservableList<Visit> observableHistory = visitHistory.getObservableVisits();

        if (visitIndex == EMPTY_VISIT_LOG) {
            ObservableList<Visit> copyOfObservableHistory = observableHistory;

            return new CommandResult(MESSAGE_MISSING_INDEX_PROMPT, copyOfObservableHistory);
        } else {
            try {
                Visit visit = visitHistory.getVisitByIndex(visitIndex);
                ObservableList<Visit> copyOfObservableHistory = observableHistory;

                return new CommandResult(String.format(MESSAGE_EDIT_VISIT_PROMPT, patientToEdit),
                        copyOfObservableHistory, visit, visitIndex, patientIndex.getOneBased());
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_VISIT_HISTORY_INDEX);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVisitCommand)) {
            return false;
        }

        // state check
        EditVisitCommand e = (EditVisitCommand) other;
        return patientIndex.equals(e.patientIndex)
                && visitIndex == e.visitIndex;
    }
}


