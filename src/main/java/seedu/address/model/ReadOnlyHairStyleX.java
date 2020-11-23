package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;


/**
 * Unmodifiable view of a HairStyleX
 */
public interface ReadOnlyHairStyleX {


    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the hairdressers list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Hairdresser> getHairdresserList();

    /**
     * Returns an unmodifiable view of the appointments list.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns IdCounter that keeps track of hairdressers', clients' and appointments' current max ID.
     */
    IdCounter getIdCounter();

    CommandShortcutSet getCommandAliasSet();
}
