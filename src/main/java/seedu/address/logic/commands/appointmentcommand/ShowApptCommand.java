package seedu.address.logic.commands.appointmentcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.patient.NricPredicate;

/**
 * Displays all appointments for a patient in Hospify.
 */
public class ShowApptCommand extends Command {

    public static final String COMMAND_WORD = "showAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show Appointment(s) of a patient\n"
            + "Parameters: [NRIC]\n"
            + "Example: " + COMMAND_WORD + " S1234567A\n";

    private final NricPredicate predicate;

    public ShowApptCommand(NricPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        assert model.getFilteredPatientList().size() <= 1 : "Found more than 1 person for showAppt";
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowApptCommand // instanceof handles nulls
                && predicate.equals(((ShowApptCommand) other).predicate)); // state check
    }
}
