package seedu.address.logic.commands.appointmentcommand;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.ui.MainWindow;

/**
 * Displays all appointments for a patient in Hospify.
 */
public class ShowApptCommand extends Command {

    public static final String COMMAND_WORD = "showAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show Appointment(s) of a patient\n"
            + "Parameters: NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567A\n";

    private final NricPredicate predicate;

    public ShowApptCommand(NricPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Patient> filteredList = model.getFilteredPatientList().filtered(predicate);

        if (filteredList.size() != 1) {
            throw new CommandException("No patient with the NRIC found!");
        }

        MainWindow.updateAppointmentWindow(filteredList.get(0));

        return new CommandResult(
                String.format(Messages.MESSAGE_SUCCESS_FINDING_SINGLE_NRIC,
                        "Appointment Window opened!"),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowApptCommand // instanceof handles nulls
                && predicate.equals(((ShowApptCommand) other).predicate)); // state check
    }
}
