package seedu.homerce.logic.commands.service;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.ui.servicepanel.ServiceListPanel;

/**
 * Deletes a service identified using it's displayed index from homerce's service list.
 */
public class DeleteServiceCommand extends Command {

    public static final String COMMAND_WORD = "deletesvc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the service identified by the index number used in the displayed service list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SERVICE_SUCCESS = "Deleted Service: %1$s";

    private final Index targetIndex;

    public DeleteServiceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        List<Service> lastShownList = model.getFilteredServiceList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_SERVICES_INVALID_SERVICE_DISPLAYED_INDEX);
        }

        List<Appointment> appointments = model.getAppointmentManager().getAppointmentList();
        Service serviceToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (!isValidDeletion(serviceToDelete, appointments)) { // If service exists in appointment today or in future
            throw new CommandException(Messages.MESSAGES_SERVICES_INVALID_DELETION);
        }

        model.deleteService(serviceToDelete);
        return new CommandResult(
            String.format(MESSAGE_DELETE_SERVICE_SUCCESS, serviceToDelete),
            ServiceListPanel.TAB_NAME
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteServiceCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteServiceCommand) other).targetIndex)); // state check
    }

    /**
     * Checks if the service that is about to be deleted exists in Homerce's future appointments.
     *
     * Deletion will be prevented if the service exists in Homerce's appointments dated today or in the future.
     * This prevents two different services with the same service code existing in Homerce's upcoming appointments.
     *
     * @param serviceToDelete the service to be deleted.
     * @param appointments all the appointments that have been scheduled in Homerce.
     * @return a boolean indicating whether or not the service is safe to delete.
     */
    public static boolean isValidDeletion(Service serviceToDelete, List<Appointment> appointments) {
        if (appointments == null) {
            return true;
        }
        return appointments.stream().noneMatch((appointment) -> checkValidity(appointment, serviceToDelete));
    }

    /**
     * Performs check for validity of deletion.
     *
     * @param appointment is a specific appointment that has been scheduled in Homerce.
     * @param serviceToDelete the service to be deleted.
     * @return a boolean indicating whether or not the service is safe to delete.
     */
    private static boolean checkValidity(Appointment appointment, Service serviceToDelete) {
        LocalDate today = LocalDate.now();
        LocalDate appointmentDate = appointment.getAppointmentDate().getLocalDate();
        Service appointmentService = appointment.getService();
        return (appointmentDate.isAfter(today) || appointmentDate.isEqual(today))
            && appointmentService.equals(serviceToDelete);

    }
}
