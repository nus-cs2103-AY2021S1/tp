package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HairStyleX hairStyleX;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Hairdresser> filteredHairdressers;
    private final FilteredList<Appointment> filteredAppointments;
    private final SimpleObjectProperty<Appointment> selectedAppointment = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyHairStyleX hairStyleX, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(hairStyleX, userPrefs);

        logger.fine("Initializing with address book: " + hairStyleX + " and user prefs " + userPrefs);

        this.hairStyleX = new HairStyleX(hairStyleX);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.hairStyleX.getClientList());
        filteredHairdressers = new FilteredList<>(this.hairStyleX.getHairdresserList());
        filteredAppointments = new FilteredList<>(this.hairStyleX.getAppointmentList());
    }

    public ModelManager() {
        this(new HairStyleX(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getHairStyleXFilePath() {
        return userPrefs.getHairStyleXFilePath();
    }

    @Override
    public void setHairStyleXFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setHairStyleXFilePath(addressBookFilePath);
    }

    //=========== HairStyleX ================================================================================

    @Override
    public void setHairStyleX(ReadOnlyHairStyleX hairStyleX) {
        this.hairStyleX.resetData(hairStyleX);
    }

    @Override
    public ReadOnlyHairStyleX getHairStyleX() {
        return hairStyleX;
    }

    @Override
    public Client getClientById(ClientId clientId) {
        requireNonNull(clientId);
        return hairStyleX.getClientById(clientId);
    }

    @Override
    public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
        requireNonNull(hairdresserId);
        return hairStyleX.getHairdresserById(hairdresserId);
    }

    @Override
    public Appointment getAppointmentById(AppointmentId appointmentId) {
        requireNonNull(appointmentId);
        return hairStyleX.getAppointmentById(appointmentId);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return hairStyleX.hasPerson(person);
    }

    //=========== Client =============================================================

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return hairStyleX.hasClient(client);
    }

    @Override
    public void deleteClient(Client client) {
        hairStyleX.removeClient(client);
        hairStyleX.updateAppointmentWhenClientDeleted(client.getId());
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void addClient(Client client) {
        hairStyleX.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        hairStyleX.setClient(target, editedClient);
        hairStyleX.updateAppointmentWhenClientIsUpdated(target.getId(), editedClient);
    }

    //=========== Hairdresser =============================================================

    @Override
    public boolean hasHairdresser(Hairdresser person) {
        requireNonNull(person);
        return hairStyleX.hasHairdresser(person);
    }

    @Override
    public void deleteHairdresser(Hairdresser target) {
        hairStyleX.removeHairdresser(target);
        hairStyleX.updateAppointmentWhenHairdresserDeleted(target.getId());
        updateFilteredHairdresserList(PREDICATE_SHOW_ALL_HAIRDRESSERS);

        logger.info("Model: Deleted Hairdresser " + target.getId());
    }

    @Override
    public void addHairdresser(Hairdresser person) {
        hairStyleX.addHairdresser(person);
        updateFilteredHairdresserList(PREDICATE_SHOW_ALL_HAIRDRESSERS);

        logger.info("Model: Added Hairdresser " + person.getId());
    }

    @Override
    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        requireAllNonNull(target, editedHairdresser);

        hairStyleX.setHairdresser(target, editedHairdresser);
        hairStyleX.updateAppointmentWhenHairdresserIsUpdated(target.getId(), editedHairdresser);

        logger.info("Model: Edited Hairdresser " + editedHairdresser.getId());
    }

    //=========== Appointment =============================================================
    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return hairStyleX.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        hairStyleX.removeAppointment(target);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        logger.info("Model: Deleted Appointment " + target.getId());
    }

    @Override
    public void addAppointment(Appointment appointment) {
        hairStyleX.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        logger.info("Model: Added Appointment " + appointment.getId());
    }

    @Override
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        requireAllNonNull(target, changedAppointment);
        hairStyleX.setAppointment(target, changedAppointment);
        logger.info("Model: Edited Appointment " + target.getId());
    }

    /**
     * Returns an unmodifiable view of the appointments list.
     * This list will not contain any duplicate appointments.
     */
    public List<Appointment> getAppointmentList() {
        return hairStyleX.getAppointmentList();
    }

    //=========== Filtered Client List Accessors =============================================================

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
        logger.info("Client list gets filtered");
    }

    //=========== Filtered Hairdresser List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Hairdresser} backed by the internal list of
     * {@code versionedHairStyleX}
     */
    @Override
    public ObservableList<Hairdresser> getFilteredHairdresserList() {
        return filteredHairdressers;
    }

    @Override
    public void updateFilteredHairdresserList(Predicate<Hairdresser> predicate) {
        requireNonNull(predicate);
        filteredHairdressers.setPredicate(predicate);
        logger.info("Hairdresser list gets filtered");
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedDocX}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
        logger.info("Appointment list gets filtered");
    }

    //=========== Selected appointment ======================================================================

    @Override
    public ReadOnlyProperty<Appointment> selectedAppointmentProperty() {
        return selectedAppointment;
    }

    @Override
    public Appointment getSelectedAppointment() {
        return selectedAppointment.getValue();
    }

    @Override
    public void setSelectedAppointment(Appointment appointment) {
        if (appointment != null && !filteredAppointments.contains(appointment)) {
            throw new AppointmentNotFoundException();
        }
        selectedAppointment.setValue(appointment);
    }

    /**
     * Ensures {@code selectedAppointment} is a valid appointment in {@code filteredAppointments}.
     */
    private void ensureSelectedAppointmentIsValid(ListChangeListener.Change<? extends Appointment> change) {
        while (change.next()) {
            if (selectedAppointment.getValue() == null) {
                // null is always a valid selected appointment, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedAppointmentReplaced =
                    change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                            && change.getRemoved().contains(selectedAppointment.getValue());
            if (wasSelectedAppointmentReplaced) {
                // Update selectedAppointment to its new value.
                int index = change.getRemoved().indexOf(selectedAppointment.getValue());
                selectedAppointment.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedAppointmentRemoved = change.getRemoved().stream()
                    .anyMatch(removedAppointment -> selectedAppointment.getValue()
                            .isSameAppointment(removedAppointment));
            if (wasSelectedAppointmentRemoved) {
                // Select the appointment that came before it in the list,
                // or clear the selection if there is no such appointment.
                selectedAppointment.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return hairStyleX.equals(other.hairStyleX)
                && userPrefs.equals(other.userPrefs);
    }




}
