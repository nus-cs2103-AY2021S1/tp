package seedu.homerce.logic.commands.service;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.ui.servicepanel.ServiceListPanel;

/**
 * Clears the service list.
 */
public class ClearServiceCommand extends Command {

    public static final String COMMAND_WORD = "clearsvc";
    public static final String MESSAGE_SUCCESS = "All services has been cleared!";


    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);

        List<Service> allServices = model.getServiceManager().getServiceList();
        List<Appointment> allAppointments = model.getAppointmentManager().getAppointmentList();

        boolean isAnyServiceScheduled = allServices.stream()
            .anyMatch(service -> !DeleteServiceCommand.isValidDeletion(service, allAppointments));

        if (isAnyServiceScheduled) {
            throw new CommandException("Can not clear services that are already scheduled in upcoming appointments."
            + "\nPlease remove these appointments before clearing your services.");
        }

        model.setServiceManager(new ServiceManager());
        return new CommandResult(MESSAGE_SUCCESS, ServiceListPanel.TAB_NAME);
    }
}
