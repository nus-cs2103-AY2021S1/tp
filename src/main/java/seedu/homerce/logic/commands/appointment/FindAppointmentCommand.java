package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;

public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "findapt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments of which its details contains "
        + "exactly one of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: [p/PHONE_NUMBER]* [n/NAME]* [dt/DATE]* [s/SERVICE_CODE]*\n"
        + "Example: " + COMMAND_WORD + " p/98429700";

    private final Predicate<Appointment> predicate;

    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    public Predicate<Appointment> getPredicate() {
        return predicate;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentList().size()),
            AppointmentListPanel.TAB_NAME
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindAppointmentCommand // instanceof handles nulls
            && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}
