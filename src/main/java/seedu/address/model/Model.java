package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Hairdresser> PREDICATE_SHOW_ALL_HAIRDRESSERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' hairStyleX file path.
     */
    Path getHairStyleXFilePath();

    /**
     * Sets the user prefs' hairStyleX file path.
     */
    void setHairStyleXFilePath(Path hairStyleXFilePath);

    /**
     * Replaces hairStyleX data with the data in {@code hairStyleX}.
     */
    void setHairStyleX(ReadOnlyHairStyleX hairStyleX);

    /** Returns the HairStyleX */
    ReadOnlyHairStyleX getHairStyleX();

    /**
     * Return object Client with given id
     */
    Client getClientById(ClientId clientId);

    /**
     * Return object Hairdresser with given id
     */
    Hairdresser getHairdresserById(HairdresserId hairdresserId);

    /**
     * Return object Appointment with given id
     */
    Appointment getAppointmentById(AppointmentId appointmentId);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the hairStyleX.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the hairStyleX.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the hairStyleX.
     */
    void deleteClient(Client client);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the hairStyleX.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the hairStyleX.
     * The person identity of {@code editedClient} must not be the same as another existing client in the hairStyleX.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);


    /**
     * Returns true if a hairdresser with the same identity as {@code hairdresser} exists in the hairStyleX.
     */
    boolean hasHairdresser(Hairdresser hairdresser);

    /**
     * Deletes the given hairdresser.
     * The hairdresser must exist in the hairStyleX.
     */
    void deleteHairdresser(Hairdresser target);

    /**
     * Adds the given hairdresser.
     * {@code hairdresser} must not already exist in the hairStyleX.
     */
    void addHairdresser(Hairdresser hairdresser);

    /**
     * Replaces the given hairdresser {@code target} with {@code editedHairdresser}.
     * {@code target} must exist in the hairStyleX.
     * The hairdresser identity of {@code editedHairdresser} must not be
     * the same as another existing hairdresser in the hairStyleX.
     */
    void setHairdresser(Hairdresser target, Hairdresser editedHairdresser);

    /** Returns an unmodifiable view of the filtered hairdresser list */
    ObservableList<Hairdresser> getFilteredHairdresserList();

    /**
     * Updates the filter of the filtered hairdresser list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHairdresserList(Predicate<Hairdresser> predicate);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the HairStyleX.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Adds the appointment.
     * {@code appointment} must not be a duplicate
     */
    void addAppointment(Appointment appointment);

    /**
     * Adds the appointment.
     * {@code appointment} must not be a duplicate
     */
    void deleteAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code changedAppointment}.
     * {@code target} must exist in the HairStyleX.
     * The new appointment must not be the same as another existing appointment in HairStyleX.
     */
    void setAppointment(Appointment target, Appointment changedAppointment);

    List<Appointment> getAppointmentList();
    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the selected appointment in the filtered appointment list.
     * null if no appointment is selected.
     */
    Appointment getSelectedAppointment();

    /**
     * Sets the selected appointment in the filtered appointment list.
     */
    void setSelectedAppointment(Appointment appointment);


    /**
     * Selected appointment in the filtered appointment list.
     * null if no appointment is selected.
     */
    ReadOnlyProperty<Appointment> selectedAppointmentProperty();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code appointment}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
