package seedu.homerce.logic.commands.client;

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
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.clientpanel.ClientListPanel;

/**
 * Deletes a client identified using it's displayed index from homerce .
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_WORD = "deletecli";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";

    private final Index targetIndex;

    public DeleteClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        List<Appointment> appointments = model.getAppointmentManager().getAppointmentList();
        Client clientToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (!isValidDeletion(clientToDelete, appointments)) { // If client exists in appointment today or in future
            throw new CommandException(Messages.MESSAGE_CLIENT_INVALID_DELETION);
        }

        model.deleteClient(clientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete),
                ClientListPanel.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }

    /**
     * Checks if the client that is about to be deleted exists in Homerce's future appointments.
     * Deletion will be prevented if the client exists in Homerce's appointments dated today or in the future.
     */
    public static boolean isValidDeletion(Client clientToDelete, List<Appointment> appointments) {
        if (appointments == null) {
            return true;
        }
        return appointments.stream().noneMatch((appointment) -> checkValidity(appointment, clientToDelete));
    }

    /**
     * Performs check for validity of deletion.
     */
    private static boolean checkValidity(Appointment appointment, Client clientToDelete) {
        LocalDate today = LocalDate.now();
        LocalDate appointmentDate = appointment.getAppointmentDate().getLocalDate();
        Client appointmentClient = appointment.getClient();
        return (appointmentDate.isAfter(today) || appointmentDate.isEqual(today))
                && appointmentClient.equals(clientToDelete);

    }
}
